package com.ljc.shop3.vo;

import com.ljc.shop3.model.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
public class CategoriesAllVO {
    private List<CategoryPureVO> roots;
    private List<CategoryPureVO> subs;

    public CategoriesAllVO(Map<Integer, List<Category>> map) {
//        List<Category> roots = map.get(1);
//        roots.forEach(category->{
//            CategoryPureVO vo = new CategoryPureVO(category);
//            this.roots.add(vo);
//        });
        this.roots = map.get(1).stream()
                .map(CategoryPureVO::new)
                .collect(Collectors.toList());
        this.subs = map.get(2).stream()
                .map(CategoryPureVO::new)
                .collect(Collectors.toList());
    }


}
