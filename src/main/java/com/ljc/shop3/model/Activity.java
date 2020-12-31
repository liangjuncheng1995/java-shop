package com.ljc.shop3.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Where(clause = "delete_time is null and online=1")
public class Activity extends BaseEntity{
    @Id
    private Long id;
    private String title;
    private String name;
    private String description;

    private Date startTime;
    private Date endTime;
    private Boolean online;

    private String entranceImg; //活动入口的图片
    private String internalTopImg; //活动顶部的图片

    private String remark;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="activityId")
    private List<Coupon> couponList;


}
