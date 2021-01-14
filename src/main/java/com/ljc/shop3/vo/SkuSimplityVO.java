package com.ljc.shop3.vo;


import com.ljc.shop3.model.Sku;
import com.ljc.shop3.model.Spec;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SkuSimplityVO {

    public SkuSimplityVO(Sku sku) {
        this.id = sku.getId();
        this.category_id = sku.getCategoryId();
        this.root_category_id = sku.getRootCategoryId();
        this.spu_id = sku.getSpuId();
        this.price = sku.getPrice();
        this.discount_price = sku.getDiscountPrice();
        this.online = sku.getOnline();
        this.img = sku.getImg();
        this.specs = sku.getSpecs();
        this.code = sku.getCode();
        this.stock = sku.getStock();
        this.is_test = true;
    }

    private Long id;
    private Long category_id;
    private Long root_category_id;
    private Long spu_id;
    private BigDecimal price;
    private BigDecimal discount_price;
    private Boolean online;
    private String img;
    private List<Spec> specs;
    private String code;
    private Long stock;
    private Boolean is_test;
}
