package com.ljc.shop3.service;


import com.ljc.shop3.core.enumeration.CouponStatus;
import com.ljc.shop3.exception.http.NotFoundException;
import com.ljc.shop3.exception.http.ParameterException;
import com.ljc.shop3.model.Activity;
import com.ljc.shop3.model.Coupon;
import com.ljc.shop3.model.UserCoupon;
import com.ljc.shop3.repository.ActivityRepository;
import com.ljc.shop3.repository.CouponRepository;
import com.ljc.shop3.repository.UserCouponRepository;
import com.ljc.shop3.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CouponService {
    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private UserCouponRepository userCouponRepository;

    public List<Coupon> getByCategory(Long cid) {
        Date now = new Date();
        return couponRepository.findByCategory(cid, now);
    }

    public List<Coupon> getWholeStoreCoupons() {
        Date now = new Date();
        return couponRepository.findByWholeStore(true, now);
    }

    public List<Coupon> getMyAvailableCoupons(Long uid) {
        Date now = new Date();
        return this.couponRepository.findMyAvailable(uid, now);
    }

    public List<Coupon> getMyUsedCoupons(Long uid) {
        Date now = new Date();
        return this.couponRepository.findMyUsed(uid, now);
    }

    public  List<Coupon> getMyExpiredCoupons(Long uid) {
        Date now = new Date();
        return this.couponRepository.findMyExpired(uid, now);
    }

    public void collectOneCoupon(Long uid, Long couponId) {
        this.couponRepository.findById(couponId)
                .orElseThrow(() -> new NotFoundException(40003));

        Activity activity = this.activityRepository.findByCouponListId(couponId)
                .orElseThrow(() -> new NotFoundException(40010));

        Date now = new Date();

        Boolean isIn = CommonUtil.isInTimeLine(now, activity.getStartTime(), activity.getEndTime());
        if(!isIn) {
            throw new ParameterException(40005);
        }

        this.userCouponRepository.findFirstByUserIdAndCouponId(uid, couponId)
                .ifPresent((uc) -> {throw new ParameterException(40006);});

        UserCoupon userCouponNew = UserCoupon.builder()
                .userId(uid)
                .couponId(couponId)
                .status(CouponStatus.AVATlABLE.getValue())
                .createTime(now)
                .build();
        userCouponRepository.save(userCouponNew);

    }
}
