package com.ljc.shop3.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Spu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String subtitle;

    @ManyToMany(mappedBy = "spuList")
    private List<Theme> themeList;
}
