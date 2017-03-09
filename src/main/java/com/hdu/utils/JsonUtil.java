package com.hdu.utils;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonUtil {

    private static ObjectMapper wrapper=new ObjectMapper();
    static{
        wrapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"));
        wrapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }

    public static String toString(Object json)throws Exception{
        return wrapper.writeValueAsString(json);
    }

    public static <T> T toObject(String json,Class<T> clazz) throws Exception {
        return wrapper.readValue(json,clazz);
    }

}
