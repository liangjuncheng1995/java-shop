package com.ljc.shop3.model;

import javax.persistence.*;

@Entity //表达表的数据结构
public class BannerItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String img;

    private String keyword;

    private Short type;

    private String name;

    private long bannerId; //外键

    //foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT)禁用物理的外键
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT), insertable = false, updatable = false, name = "bannerId")
    private Banner banner;
}
