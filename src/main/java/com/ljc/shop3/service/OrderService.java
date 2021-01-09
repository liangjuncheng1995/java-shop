package com.ljc.shop3.service;

import com.ljc.shop3.core.LocalUser;
import com.ljc.shop3.core.enumeration.OrderStatus;
import com.ljc.shop3.core.money.IMoneyDiscount;
import com.ljc.shop3.dto.OrderDTO;
import com.ljc.shop3.dto.SkuInfoDTO;
import com.ljc.shop3.exception.http.ForbiddenException;
import com.ljc.shop3.exception.http.NotFoundException;
import com.ljc.shop3.exception.http.ParameterException;
import com.ljc.shop3.logic.CouponChecker;
import com.ljc.shop3.logic.OrderChecker;
import com.ljc.shop3.model.*;
import com.ljc.shop3.repository.CouponRepository;
import com.ljc.shop3.repository.OrderRepository;
import com.ljc.shop3.repository.SkuRepository;
import com.ljc.shop3.repository.UserCouponRepository;
import com.ljc.shop3.util.CommonUtil;
import com.ljc.shop3.util.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private SkuService skuService;

    @Autowired
    CouponRepository couponRepository;

    @Autowired
    UserCouponRepository userCouponRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SkuRepository skuRepository;

    @Autowired
    IMoneyDiscount iMoneyDiscount;

    @Value("${shop3.order.max-sku-limit}")
    private int maxTimeLimit;

    @Value("${shop3.order.pay-time-limit}")
    private Integer payTimeLimit;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Transactional
    public Long placeOrder(Long uid, OrderDTO orderDTO, OrderChecker orderChecker) {
        String orderNo = OrderUtil.makeOrderNo();
        Calendar now = Calendar.getInstance();
        Calendar now1 = (Calendar) now.clone();
        Date expiredTime = CommonUtil.addSomeSeconds(now, this.payTimeLimit).getTime();

        Order order = Order.builder()
                .orderNo(orderNo)
                .totalPrice(orderDTO.getTotalPrice())
                .finalTotalPrice(orderDTO.getFinalTotalPrice())
                .userId(uid)
                .totalCount(orderChecker.getTotalCount().longValue())
                .snapImg(orderChecker.getLeaderImg())
                .snapTitle(orderChecker.getLeaderTitle())
                .status(OrderStatus.UNPAID.getValue())
                .expiredTime(expiredTime)
                .placedTime(now1.getTime())
                .build();

        order.setSnapAddress(orderDTO.getAddress());
        order.setSnapItems(orderChecker.getOrderSkuList());

        this.orderRepository.save(order);
        this.reduceStock(orderChecker);

        //减库存reduceStock
        //核销优惠券
        //加入到延迟消息队列

        Long couponId = -1L;

        if(orderDTO.getCouponId() != null) {
            this.writeOffCoupon(orderDTO.getCouponId(), order.getId(), uid);
            couponId = orderDTO.getCouponId();
        }
        this.sendToRedis(order.getId(), uid, couponId);
        return order.getId();
    }

    private void sendToRedis(Long uid, Long oid, Long couponId) {
        String key = oid.toString() + "," + uid.toString() + "," + couponId.toString();
        try {
            stringRedisTemplate.opsForValue().set(key, "1", this.payTimeLimit, TimeUnit.SECONDS);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Page<Order> getUnpaid(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createTime").descending());
        Long uid = LocalUser.getUser().getId();

        Date now = new Date();

        return this.orderRepository.findByExpiredTimeGreaterThanAndStatusAndUserId(
             now,
             OrderStatus.UNPAID.getValue(),
             uid, pageable
        );
    }

    public Page<Order> getByStatus(Integer status, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createTime").descending());
        Long uid = LocalUser.getUser().getId();
        if(status == OrderStatus.ALL.getValue()) {
            return this.orderRepository.findByUserId(uid, pageable);
        }
        return this.orderRepository.findByUserIdAndStatus(uid, status, pageable);
    }

    public Optional<Order> getOrderDetail(Long oid) {
        Long uid = LocalUser.getUser().getId();
        return this.orderRepository.findFirstByUserIdAndId(uid, oid);
    }

    public void updateOrderPrepayId(Long orderId, String prePayId) {
        Optional<Order> order = this.orderRepository.findById(orderId);
        order.ifPresent(o ->{
            o.setPrepayId(prePayId);
            this.orderRepository.save(o);
        });
        order.orElseThrow(() -> new NotFoundException(10007));
    }

    private void writeOffCoupon(Long couponId, Long oid, Long uid) {
        int result = this.userCouponRepository.writeOff(couponId, oid, uid);
        if(result != 1) {
            throw new ForbiddenException(40012);
        }
    }

    private void reduceStock(OrderChecker orderChecker) {
        List<OrderSku> orderSkuList = orderChecker.getOrderSkuList();
        for(OrderSku orderSku : orderSkuList) {
            System.out.println(orderSku.getCount().longValue());
            int result = this.skuRepository.reduceStock(orderSku.getId(), orderSku.getCount().longValue());
            if(result != 1) {
                throw new ParameterException(50003);
            }
        }

    }

    public OrderChecker isOk(Long uid, OrderDTO orderDTO) {
        if(orderDTO.getFinalTotalPrice().compareTo(new BigDecimal("0")) <= 0) {
            throw new ParameterException(50011);
        }

        List<Long> skuIdList = orderDTO.getSkuInfoList()
                .stream().map(SkuInfoDTO::getId)
                .collect(Collectors.toList());

        List<Sku>  skuList = skuService.getSkuListByIds(skuIdList);



        Long couponId = orderDTO.getCouponId();
        CouponChecker couponChecker = null;

        if(couponId != null) {
            Coupon coupon = this.couponRepository.findById(couponId)
                    .orElseThrow(() -> new NotFoundException(40004));
            UserCoupon userCoupon =
                    this.userCouponRepository.findFirstByUserIdAndCouponIdAndStatus(uid, couponId, 1)
                    .orElseThrow(() -> new NotFoundException(50006));
            couponChecker = new CouponChecker(coupon, iMoneyDiscount);

        }
        OrderChecker  orderChecker = new OrderChecker(
                orderDTO,skuList,couponChecker,maxTimeLimit
        );

        orderChecker.isOK();
        return orderChecker;
    }
}
