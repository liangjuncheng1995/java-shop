package com.ljc.shop3.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;


@Getter
@Setter
@Entity
public class BannerItem extends BaseEntity{
    @Id
    private Long id;
    private String img;
    private String keyword;
    private short type;

    private Long bannerId;
    private String name;


}
