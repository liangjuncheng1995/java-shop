package com.ljc.shop3.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljc.shop3.exception.http.ServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Convert
public class ListAndJson implements AttributeConverter<List<Object>, String> {
    @Autowired
    private ObjectMapper mapper;

    @Override
    public String convertToDatabaseColumn(List<Object> objects) { //序列化
        try {
            return mapper.writeValueAsString(objects);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServerErrorException(9999);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Object> convertToEntityAttribute(String s) { //反序列化
        try {
            if(s == null) {
                return null;
            }
            List<Object> t = mapper.readValue(s, List.class);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServerErrorException(9999);
        }
    }
}
