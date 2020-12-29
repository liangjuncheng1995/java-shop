package com.ljc.shop3.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@Setter
@Where(clause = "delete_time is null")
public class GridCategory extends BaseEntity{
    @Id
    private Long id;
    private String title;
    private String name;
    private Long categoryId;
    private Long rootCategoryId;
    private String img;

}
