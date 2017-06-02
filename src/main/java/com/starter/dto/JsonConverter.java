package com.starter.dto;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by adam.wells on 3/04/2016.
 */
public class JsonConverter {

    private static ObjectMapper mapper = new ObjectMapper();
    private static TypeReference<HashMap<String, Object>> typeRefMap = new TypeReference<HashMap<String, Object>>() {};

    public static HashMap<String, Object> fromJson(String jsonString) {

        try {
            return mapper.readValue(new ByteArrayInputStream(jsonString.getBytes("UTF-8")), typeRefMap);
        } catch (IOException e) {
            return new HashMap<>();
        }

    }

    public static <T extends Object> T fromJson(String jsonString, Class<T> typeReference) {

        try {
            return mapper.readValue(jsonString, typeReference);
        } catch (IOException e) {
            return null;
        }

    }

    public static <T extends Object> List<T> fromJsonDataAsList(String jsonString, Class<T> typeReference) {

        HashMap<String, Object> map = fromJson(jsonString);
        List<Map<String,Object>> list = (List<Map<String,Object>>) map.get("data");

        List<T> result = new ArrayList<>();

        for (Map<String,Object> item : list){
            result.add(fromJson(toJson(item),typeReference));
        }

        return result;

    }

    public static <T extends Object> T fromJsonDataAsObject(String jsonString, Class<T> typeReference) {

        HashMap<String, Object> map = fromJson(jsonString);
        Map<String,Object> item = (Map<String,Object>) map.get("data");

        return fromJson(toJson(item),typeReference);
    }

    public static String toJson(Object object) {

        try {
            return mapper.writeValueAsString(object);
        } catch (IOException e) {
            return null;
        }

    }

    public static HashMap<String, Object> toMap(Object object) {

        try {
            return fromJson(mapper.writeValueAsString(object));
        } catch (IOException e) {
            return null;
        }

    }

    public static String toJsonPretty(Object object) {

        if (object.getClass().equals(String.class)) {
            object = fromJson((String) object);
        }

        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (IOException e) {
            return null;
        }

    }

}
