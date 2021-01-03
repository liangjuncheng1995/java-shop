package com.ljc.shop3.repository;

import com.ljc.shop3.model.UserCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCouponRepository extends JpaRepository<UserCoupon, Long > {
    Optional<UserCoupon> findFirstByUserIdAndCouponId(Long uid, Long couponId);
}
