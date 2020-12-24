package com.ljc.shop3.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "banner") //重命名数据表
//orm
//物理外键 逻辑外键 实体与实体关系配置 提高开发效率
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 16)
    private String name;

    @Transient //不生成对应的字段
    private String description;

    private String img;

    private String title;

    @OneToMany(mappedBy = "banner") //@OneToMany(fetch = FetchType.EAGER) 急加载，通常不推荐使用
//    @org.hibernate.annotations.ForeignKey(name = "null") //去除物理的外键
//    @JoinColumn(name = "bannerId") //外键的设计
    private List<BannerItem> items;
}