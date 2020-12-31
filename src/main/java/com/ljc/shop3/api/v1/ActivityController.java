package com.ljc.shop3.api.v1;


import com.ljc.shop3.exception.http.NotFoundException;
import com.ljc.shop3.model.Activity;
import com.ljc.shop3.service.ActivityService;
import com.ljc.shop3.vo.ActivityCouponVO;
import com.ljc.shop3.vo.ActivityPureVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("activity")
@RestController
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @GetMapping("/name/{name}")
    public ActivityPureVO getHomeActivity(@PathVariable String name) {
        Activity activity = activityService.getByName(name);
        if(activity == null) {
            throw new NotFoundException(40001);
        }
        ActivityPureVO vo = new ActivityPureVO(activity);
        return vo;
    }

    @GetMapping("/name/{name}/with_coupon")
    public ActivityCouponVO getActivityWithCoupons(@PathVariable String name) {
        Activity activity = activityService.getByName(name);
        if(activity == null) {
            throw new NotFoundException(40001);
        }
        return new ActivityCouponVO(activity);
    }
}
