package com.ljc.shop3.vo;

import com.ljc.shop3.model.Category;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter

public class CategoryPureVO {
    private Long id;

    private String name;

//    private String description;

    private Boolean isRoot;

    private Long parentId;

    private String img;

    private Long index;

    public CategoryPureVO(Category category) {
        BeanUtils.copyProperties(category, this);
    }
}
