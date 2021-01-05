package com.ljc.shop3.logic;

import com.ljc.shop3.bo.SkuOrderBO;
import com.ljc.shop3.core.enumeration.CouponType;
import com.ljc.shop3.core.money.HalfUpRound;
import com.ljc.shop3.core.money.IMoneyDiscount;
import com.ljc.shop3.exception.http.ForbiddenException;
import com.ljc.shop3.exception.http.ParameterException;
import com.ljc.shop3.model.Category;
import com.ljc.shop3.model.Coupon;
import com.ljc.shop3.model.User;
import com.ljc.shop3.model.UserCoupon;
import com.ljc.shop3.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class CouponChecker {

    private Coupon coupon;

    private IMoneyDiscount iMoneyDiscount;

    public CouponChecker(Coupon coupon, IMoneyDiscount iMoneyDiscount) {
        this.coupon = coupon;
        this.iMoneyDiscount = iMoneyDiscount;
    }

    public void isOk() {
        Date now = new Date();
        Boolean isInTimeline =
                CommonUtil.isInTimeLine(now, this.coupon.getStartTime(), this.coupon.getEndTime());
        if(!isInTimeline) {
            throw new ForbiddenException(40007);
        }
    }

    public void finalTotalPriceIsOk(BigDecimal orderFinalTotalPrice,
                                    BigDecimal serverTotalPrice) {

        BigDecimal serverFinalTotalPrice;

        switch (CouponType.toType(this.coupon.getType())) {
            case FULL_MINUS:
            case NO_THRESHOLD_MINUS:
                serverFinalTotalPrice = serverTotalPrice.subtract(this.coupon.getMinus());
                if(serverFinalTotalPrice.compareTo(new BigDecimal("0")) <= 0) {
                    throw new ForbiddenException(50008);
                }
                break;
            case FULL_OFF:
                serverFinalTotalPrice = this.iMoneyDiscount.discount(serverTotalPrice, this.coupon.getRate());
                break;
            default:
                throw new ParameterException(40009);
        }
        int compare = serverFinalTotalPrice.compareTo(orderFinalTotalPrice);
        if(compare != 0) {
            throw new ForbiddenException(50008);
        }
    }

    public void canBeUsed(List<SkuOrderBO> skuOrderBOList, BigDecimal serveTotalPrice) {
        // sku price sku_model;
        // sku count order
        // sku category id sku_model;
        BigDecimal orderCategoryPrice;

        if(this.coupon.getWholeStore()) {
            orderCategoryPrice = serveTotalPrice;
        } else {
            List<Long> cidList = this.coupon.getCategoryList()
                    .stream()
                    .map(Category::getId)
                    .collect(Collectors.toList());
            orderCategoryPrice = this.getSumByCategoryList(skuOrderBOList, cidList);
        }
        this.couponCanBeUsed(orderCategoryPrice);

        // coupon category
    }

    private void couponCanBeUsed(BigDecimal orderCategoryPrice) {
        switch (CouponType.toType(this.coupon.getType())) {
            case FULL_OFF:

            case FULL_MINUS:
               int compare = this.coupon.getFullMoney().compareTo(orderCategoryPrice);
               if(compare > 0) {
                   throw new ParameterException(40008);
               }
               break;
            case NO_THRESHOLD_MINUS:
                throw new ParameterException(40009);
        }
    }

    private BigDecimal getSumByCategoryList(List<SkuOrderBO> skuOrderBOList, List<Long> cidList) {
        BigDecimal sum = cidList.stream()
                .map(cid->this.getSumByCategory(skuOrderBOList, cid))
                .reduce(BigDecimal::add)
                .orElse(new BigDecimal("0"));
        return sum;
    }

    private BigDecimal getSumByCategory(List<SkuOrderBO> skuOrderBOList, Long cid) {
        BigDecimal sum = skuOrderBOList.stream()
                .filter(sku -> sku.getCategoryId().equals(cid))
                .map(SkuOrderBO::getTotalPrice)
                .reduce(BigDecimal::add)
                .orElse(new BigDecimal("0"));
        return sum;

    }
//    public CouponChecker(Long couponId, Long uid) {
//
//    }
}
