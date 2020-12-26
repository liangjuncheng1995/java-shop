package com.ljc.shop3.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Entity
@Getter
@Setter
public class Banner extends BaseEntity{
    @Id
    private Long id;
    private String name;
    private String description;

    private String title;
    private String img;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="bannerId")
    private List<BannerItem> items;
}
