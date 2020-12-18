package com.ljc.shop3.sample;

import com.ljc.shop3.sample.condition.DianaCondition;
import com.ljc.shop3.sample.condition.IreliaCondition;
import com.ljc.shop3.sample.hero.Camille;
import com.ljc.shop3.sample.hero.Diana;
import com.ljc.shop3.sample.hero.Irelia;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

@Configuration
public class HeroConfiguration {
//    @Bean
//    @ConditionalOnMissingBean(name="mysql")
//    @ConditionalOnMissingBean(name="mysql")
//    @ConditionalOnBean(name="mysql")
//    @ConditionalOnProperty(value = "hero.condition",havingValue = "diana", matchIfMissing = true)
    //matchIfMissing 的作用，当配置文件找不到对应的值的时候，把这个类注入到容器里面，默认值的作用
//    @Conditional(DianaCondition.class)
//    public ISkill diana() {
//        return new Diana("Diana", 18);
//    }

//    @ConditionalOnProperty(value = "hero.condition",havingValue = "irelia")
//    @Conditional(IreliaCondition.class)
    @Bean
    public ISkill irelia() {
        return new Irelia();
    }
}
