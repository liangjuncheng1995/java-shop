package com.ljc.shop3.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljc.shop3.exception.http.ServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GenericAndJson {
    private static ObjectMapper mapper;

    @Autowired
    public void setMapper(ObjectMapper mapper) {//静态方法的依赖注入
        GenericAndJson.mapper = mapper;
    }

    public static <T> String objectToJson(T o) { //序列化
        try {
            return GenericAndJson.mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServerErrorException(9999);
        }
    }

    public static <T> T jsonToObject(String s, TypeReference<T> tr) { //反序列化
        if(s == null) {
            return null;
        }
        try {
            T o = GenericAndJson.mapper.readValue(s, tr);
            return o;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServerErrorException(9999);
        }
    }

//    public static <T> List<T> jsonToList(String s) {
//        if(s == null) {
//            return null;
//        }
//        try {
//            List<T> list = GenericAndJson.mapper.readValue(s, new TypeReference<List<T>>() {
//            });
//            return list;
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//            throw new ServerErrorException(9999);
//        }
//    }

//    public static <T> T jsonToList(String s, TypeReference<T> tr) {
//        if(s == null) {
//            return null;
//        }
//        try {
//            T list = GenericAndJson.mapper.readValue(s, tr);
//            return list;
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//            throw new ServerErrorException(9999);
//        }
//    }
}
