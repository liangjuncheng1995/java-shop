package com.ljc.shop3.api.v1;


import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.ljc.shop3.exception.http.NotFoundException;
import com.ljc.shop3.model.Theme;
import com.ljc.shop3.service.ThemeService;
import com.ljc.shop3.vo.ThemePureVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("theme")
public class ThemeController {
    @Autowired
    ThemeService themeService;

    @GetMapping("by/names")
    public List<ThemePureVO> getThemeGroupByNames(@RequestParam(name= "names") String names) {
        List<String> nameList = Arrays.asList(names.split(","));
        List<Theme> themes = themeService.findByNames(nameList);
        List<ThemePureVO> list = new ArrayList<>();
        themes.forEach(theme -> {
            Mapper mapper = DozerBeanMapperBuilder.buildDefault();
            ThemePureVO vo = mapper.map(theme, ThemePureVO.class);
            list.add(vo);
        });
        return list;
    }

    @GetMapping("/name/{name}/with_spu")
    public Theme getThemeByNameWithSpu(@PathVariable(name = "name") String themeName,
                                       @RequestParam(name = "start", defaultValue = "0") Integer start,
                                       @RequestParam(name = "count", defaultValue = "10") Integer count
                                        ) {
        Optional<Theme>  optionalTheme = this.themeService.findByName(themeName);
        return optionalTheme.orElseThrow(() -> new NotFoundException(3000));

    }

}
