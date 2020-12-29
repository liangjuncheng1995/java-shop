package com.ljc.shop3.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Where(clause = "delete_time is null and online=1")
public class Category extends BaseEntity{
    @Id
    private Long id;

    private String name;

    private String description;

    private Boolean isRoot;

    private Long parentId;

    private String img;

    private Long index;

//    private Boolean online;
//    private Object level;



}
