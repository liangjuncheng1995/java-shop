package com.ljc.shop3.api.v1;


import com.ljc.shop3.core.LocalUser;
import com.ljc.shop3.core.interceptors.ScopeLevel;
import com.ljc.shop3.model.Coupon;
import com.ljc.shop3.model.User;
import com.ljc.shop3.service.CouponService;
import com.ljc.shop3.vo.CouponPureVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RequestMapping("coupon")
@RestController
public class CouponController {
    @Autowired
    private CouponService couponService;

    @GetMapping("/by/category/{cid}")
    public List<CouponPureVO> getCouponListByCategory(@PathVariable Long cid){
        List<Coupon> coupons = couponService.getByCategory(cid);
        if(coupons.isEmpty()) {
            return Collections.emptyList(); //返回空集合
        }
        List<CouponPureVO> vos = CouponPureVO.getList(coupons);
        return vos;
    }

    @GetMapping("/whole_store")
    public List<CouponPureVO> getWholeStoreCouponList() {
        List<Coupon> coupons = this.couponService.getWholeStoreCoupons();
        if(coupons.isEmpty()) {
            return Collections.emptyList();
        }
        return CouponPureVO.getList(coupons);
    }

//    @ScopeLevel()
    @PostMapping("/collect/{id}")
    public String collectCoupon(@PathVariable Long id) {
        return "开发中";
        //不能显示用户id
//        Long uid = LocalUser.getUser().getId();
    }
}
