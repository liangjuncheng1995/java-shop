package com.ljc.shop3.core.hack;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

public class AutoPrefixUrlMapping extends RequestMappingHandlerMapping {
    @Value("${shop.api-package}")
    private String apiPackagePath;

    @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
//        String packageName = this.getPrefix(handlerType);
        //原请求链接
        RequestMappingInfo mappingInfo = super.getMappingForMethod(method, handlerType);
        System.out.println(mappingInfo);

        if(mappingInfo != null) {
            String prefix = this.getPrefix(handlerType); // /v1
            RequestMappingInfo newMappingInfo = RequestMappingInfo.paths(prefix).build().combine(mappingInfo);
            System.out.println(newMappingInfo);
            return newMappingInfo;
        }
        return mappingInfo;
    }

    private String getPrefix(Class<?> handlerType) {
        //把传进来的 com.ljc.shop3.api.v1 路径进行去除
        //去除配置文件 的com.ljc.shop3.api 路径
        String packageName = handlerType.getPackage().getName(); //com.ljc.shop3.api.v1
        String dotPath = packageName.replaceAll(this.apiPackagePath,""); //.v1
        return dotPath.replace(".","/"); // /v1
    }
}
