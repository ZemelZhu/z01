package com.zemel.framework.until;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Author: zemel
 * @Date: 2020/4/4 13:22
 */
public class JsonUntil {
    public static String objectToString(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "";
        try {
            json = objectMapper.writeValueAsString(object);
        } catch (Exception e) {
//            e.printStackTrace();
        }

        return json;
    }

    public static <T> T stringToObject(String body, Class<T> cityInfoVoClass) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(body, cityInfoVoClass);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
