package com.ljc.shop3.logic;

import com.ljc.shop3.bo.SkuOrderBO;
import com.ljc.shop3.dto.OrderDTO;
import com.ljc.shop3.dto.SkuInfoDTO;
import com.ljc.shop3.exception.http.ParameterException;
import com.ljc.shop3.model.OrderSku;
import com.ljc.shop3.model.Sku;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//@Service
//@Scope("prototype")

public class OrderChecker {
    // 调用 CouponChecker
    // OrderService
    // OrderService CouponChecker

    // orderService->OrderChecker->CouponChecker
    //外观模式 face

    //校验
    //返回数据 Order OrderChecker

    private OrderDTO orderDTO;
    private List<Sku> serverSkuList;
    private CouponChecker couponChecker;
    private Integer maxSkuLimit;

    @Getter
    private List<OrderSku> orderSkuList = new ArrayList<>();

    public OrderChecker(OrderDTO orderDTO, List<Sku> serverSkuList,
                        CouponChecker couponChecker, Integer maxSkuLimit) {
        this.orderDTO = orderDTO;
        this.serverSkuList = serverSkuList;
        this.couponChecker = couponChecker;
        this.maxSkuLimit = maxSkuLimit;
    }

    public String getLeaderImg() {
        return this.serverSkuList.get(0).getImg();
    }

    public String getLeaderTitle() {
        return this.serverSkuList.get(0).getTitle();
    }

    public Integer getTotalCount() {
        return this.orderDTO.getSkuInfoList()
                .stream()
                .map(SkuInfoDTO::getCount)
                .reduce(Integer::sum)
                .orElse(0);
    }

    public void isOK() {
        // FinalTotalPrice OrderTotalPrice ServiceTotalPrice
        // 下架的sku
        // 是否有售罄的商品
        // 5已有 买7件 超出库存的检测
        // 99 101
        //优惠券的校验

        BigDecimal serverTotalPrice = new BigDecimal("0");

        List<SkuOrderBO> skuOrderBOList = new ArrayList<>();

        this.skuNotOnSale(orderDTO.getSkuInfoList().size(), this.serverSkuList.size());

        for (int i = 0; i < this.serverSkuList.size(); i++) {
            Sku sku = this.serverSkuList.get(i);
            SkuInfoDTO skuInfoDTO = this.orderDTO.getSkuInfoList().get(i);
            this.containsSoldOutSku(sku);
            this.beyondSkuStock(sku, skuInfoDTO);
            this.beyondMaxSkuLimit(skuInfoDTO);

            serverTotalPrice = serverTotalPrice.add(this.calculateSkuOrderPrice(sku, skuInfoDTO));
            skuOrderBOList.add(new SkuOrderBO(sku, skuInfoDTO));
            this.orderSkuList.add(new OrderSku(sku, skuInfoDTO));
        }
        this.totalPriceIsOk(orderDTO.getTotalPrice(), serverTotalPrice);

        if(this.couponChecker != null) {
            this.couponChecker.isOk();
            this.couponChecker.canBeUsed(skuOrderBOList, serverTotalPrice);
            this.couponChecker.finalTotalPriceIsOk(orderDTO.getFinalTotalPrice(), serverTotalPrice);
        }
    }

    private void totalPriceIsOk(BigDecimal orderTotalPrice, BigDecimal serverTotalPrice) {
        if(orderTotalPrice.compareTo(serverTotalPrice) != 0) {
            throw new ParameterException(50005);
        }
    }

    private BigDecimal calculateSkuOrderPrice(Sku sku, SkuInfoDTO skuInfoDTO) {
        if(skuInfoDTO.getCount() <= 0) {
            throw new ParameterException(50007);
        }
        return sku.getActualPrice().multiply(new BigDecimal(skuInfoDTO.getCount()));
    }


    //是否有下架商品
    private void skuNotOnSale(int count1, int count2) {
        if(count1 != count2) {
            throw new ParameterException(50002);
        }
    }

    //是否售罄
    private void containsSoldOutSku(Sku sku) {
        if(sku.getStock() == 0) {
            throw new ParameterException(50001);
        }
    }


    //是否超卖
    private void beyondSkuStock(Sku sku, SkuInfoDTO skuInfoDTO) {
        if(sku.getStock() < skuInfoDTO.getCount()) {
            throw new ParameterException(50003);
        }
    }

    //超出购买数量限制
    private void beyondMaxSkuLimit(SkuInfoDTO skuInfoDTO) {
        if(skuInfoDTO.getCount() > this.maxSkuLimit) {
            throw new ParameterException(50004);
        }
    }
}
