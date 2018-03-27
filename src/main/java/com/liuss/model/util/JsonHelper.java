package com.liuss.model.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonHelper {
    private static ObjectMapper mapper;
    static {
        mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    public static String toJson(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }
    public static  <T> T fromJson(String json, Class<T> cls) throws IOException {
        return mapper.readValue(json, cls);
    }
}
