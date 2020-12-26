package com.example.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;

/**
 * JSON工具类.
 *
 * @author Lei
 * @since 1.0
 */
public final class JsonUtil {

    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    /**
     * JSON字符串转对象.
     *
     * @param jsonString JSON字符串
     * @param type       转换对象类型
     * @return 返回指定类型的Java对象
     */
    public static <T> T toObject(InputStream inputStream, Class<T> type) {
        try {
            return mapper.readValue(inputStream, type);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    /**
     * JSON字符串转对象.
     *
     * @param jsonString JSON字符串
     * @param type       转换对象类型
     * @return 返回指定类型的Java对象
     */
    public static <T> T toObject(String jsonString, Class<T> type) {
        try {
            return mapper.readValue(jsonString, type);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    public static <T> T toObject(String jsonString, Class<T> parametrized, Class<?>... type) {
        try {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(parametrized, type);
            return mapper.readValue(jsonString, javaType);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    /**
     * 对象转JSON字符串.
     *
     * @param object Java对象
     * @return 返回对象JSON字符串形式
     */
    public static String toJsonString(Object object) {
        return toJsonString(object, false);
    }

    /**
     * 对象转JSON字符串.
     *
     * @param object Java对象
     * @param pretty 是否美化
     * @return 返回对象JSON字符串形式
     */
    public static String toJsonString(Object object, boolean pretty) {
        if (object == null) {
            return null;
        }

        try {
            return pretty ? mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object) : mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new JsonException(e);
        }
    }

    static class JsonException extends RuntimeException {
        private static final long serialVersionUID = -5964210757214102883L;

        public JsonException(Throwable cause) {
            super(cause);
        }
    }
}
