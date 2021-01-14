package com.ljc.shop3.api.v1;

import com.ljc.shop3.model.Sku;
import com.ljc.shop3.service.SkuService;
import com.ljc.shop3.vo.SkuSimplityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("sku")
public class SkuController {
    @Autowired
    private SkuService skuService;

    @GetMapping
    public List<SkuSimplityVO> getSkus(@RequestParam List<Long> ids) {
        List<Sku> skus = skuService.getSkuListByIds(ids);
        List<SkuSimplityVO> simplityVOS = skus.stream().map(sku -> {
            return new SkuSimplityVO(sku);
        }).collect(Collectors.toList());

        return simplityVOS;
    }
}
