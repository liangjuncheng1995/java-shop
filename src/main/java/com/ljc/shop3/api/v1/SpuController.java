package com.ljc.shop3.api.v1;

import com.ljc.shop3.bo.PageCounter;
import com.ljc.shop3.exception.http.NotFoundException;
import com.ljc.shop3.model.Spu;
import com.ljc.shop3.service.SpuService;
import com.ljc.shop3.util.CommonUtil;
import com.ljc.shop3.vo.PagingDozer;
import com.ljc.shop3.vo.SpuSimplifyVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

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
    public SpuSimplifyVO getSimplifySpu(@PathVariable @Positive(message = "{id.positive}") Long id) {
        Spu spu = this.spuService.getSpu(id);
        SpuSimplifyVO vo = new SpuSimplifyVO();
        BeanUtils.copyProperties(spu, vo);
        return vo;
    }

    @GetMapping("/latest")
    public PagingDozer<Spu, SpuSimplifyVO> getLatestSpuList(@RequestParam(defaultValue = "0") Integer start,
                                                            @RequestParam(defaultValue = "10") Integer count) {

        PageCounter pageCounter = CommonUtil.coverToPageParameter(start, count);
        Page<Spu> page = this.spuService.getLatestPagingSpu(pageCounter.getPage(), pageCounter.getCount());

        return new PagingDozer<>(page, SpuSimplifyVO.class);
    }

    @GetMapping("/by/category/{id}")
//
    public PagingDozer<Spu, SpuSimplifyVO>
    getByCategoryId(
                    @PathVariable() @Positive Long id,
                    @RequestParam(name = "is_root", defaultValue = "false") Boolean isRoot,
                    @RequestParam(name = "start", defaultValue = "0") Integer start,
                    @RequestParam(name = "count", defaultValue = "10") Integer count
                    ) {
        PageCounter pageCounter = CommonUtil.coverToPageParameter(start, count);
        Page<Spu> page = this.spuService.getByCategory(id, isRoot, pageCounter.getPage(), pageCounter.getCount());
        return new PagingDozer<>(page, SpuSimplifyVO.class);
    }
}
