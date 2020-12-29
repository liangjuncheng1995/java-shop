package com.ljc.shop3.model;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@Where(clause = "delete_time is null and online=1")
public class Theme extends BaseEntity{
    @Id
    private Long id;
    private String title;
    private String description;
    private String name;
    private String extend;
    private String entranceImg;
    private String internalTopImg;
    private Boolean online;
    private String titleImg;
    private String tplName; //主题使用的模板名称

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="theme_spu", joinColumns = @JoinColumn(name="theme_id"),
        inverseJoinColumns = @JoinColumn(name="spu_id"))
    private List<Spu> spuList;
}
