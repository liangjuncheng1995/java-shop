package com.ljc.shop3.service;

import com.ljc.shop3.bo.OrderMessageBO;
import com.ljc.shop3.core.enumeration.OrderStatus;
import com.ljc.shop3.exception.http.ServerErrorException;
import com.ljc.shop3.model.Order;
import com.ljc.shop3.repository.OrderRepository;
import com.ljc.shop3.repository.UserCouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CouponBackService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserCouponRepository userCouponRepository;

    @EventListener
    @Transactional
    public void returnBack(OrderMessageBO bo) {
        Long couponId = bo.getCouponId();
        Long uid = bo.getUserId();
        Long orderId = bo.getOrderId();

        if(couponId == -1) {
            return;
        }

        Optional<Order> optional = orderRepository.findFirstByUserIdAndId(uid, orderId);
        Order order = optional.orElseThrow(() ->  new ServerErrorException(9999));

        if(order.getStatusEnum().equals(OrderStatus.UNPAID) ||
         order.getStatusEnum().equals(OrderStatus.CANCELED)) {
            this.userCouponRepository.returnBack(couponId, uid);
        }
    }
}
