package com.ljc.shop3.api.v1;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.ljc.shop3.exception.http.NotFoundException;
import com.ljc.shop3.model.Banner;
import com.ljc.shop3.model.Spu;
import com.ljc.shop3.service.BannerService;
import com.ljc.shop3.service.SpuService;
import com.ljc.shop3.vo.SpuSimplifyVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/spu")
@Validated
public class SpuController {

    @Autowired
    private SpuService spuService;

    @GetMapping("/id/{id}/detail")
    public Spu getDetail(@PathVariable @Positive Long id) {
        Spu spu = this.spuService.getSpu(id);
        if(spu == null) {
            throw new NotFoundException(30003);
        }
        return spu;
    }

    @GetMapping("id/{id}/simplify")
    public SpuSimplifyVO getSimplifySpu(@PathVariable @Positive Long id) {
        Spu spu = this.spuService.getSpu(id);
        SpuSimplifyVO vo = new SpuSimplifyVO();
        BeanUtils.copyProperties(spu, vo);
        return vo;
    }

    @GetMapping("/latest")
    public List<SpuSimplifyVO> getLatestSpuList() {

        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        List<Spu> spuList = this.spuService.getLatestPagingSpu();
        List<SpuSimplifyVO> vos = new ArrayList<>();

        spuList.forEach(s->{
            SpuSimplifyVO vo = mapper.map(s, SpuSimplifyVO.class);
            vos.add(vo);
        });
        return vos;

//        return this.spuService.getLatestPagingSpu();
    }

}
