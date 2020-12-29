package com.ljc.shop3.util;

import com.ljc.shop3.bo.PageCounter;

public class CommonUtil {
    public static PageCounter coverToPageParameter(Integer start, Integer count) {
        int pageNum = start / count;

        PageCounter pageCounter = PageCounter.builder().page(pageNum).count(count).build();
        return pageCounter;
    }
}
