package com.ljc.shop3.bo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Builder
@Builder
public class PageCounter {
    private Integer page;
    private Integer count;
}
