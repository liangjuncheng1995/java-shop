package com.ljc.shop3.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Where(clause = "delete_time is null")
public class Coupon extends BaseEntity{
    @Id
    private Long id;
    private Long activityId;
    private String title;
    private Date startTime;
    private Date endTime;
    private String description;
    private BigDecimal fullMoney; //满减的金额
    private BigDecimal minus;// 减少多少的金额
    private BigDecimal rate; //打折
    private String remark; //描述
    private Boolean wholeStore; //是否是全场券
    private Integer type; //优惠券的类型

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "couponList")
    private List<Category> categoryList;

}
