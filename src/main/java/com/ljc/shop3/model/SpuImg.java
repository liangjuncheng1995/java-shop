package com.ljc.shop3.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Setter
@Getter
public class SpuImg extends BaseEntity{
    @Id
    private Long id;

    private String img;

    private Long spuId;



}
