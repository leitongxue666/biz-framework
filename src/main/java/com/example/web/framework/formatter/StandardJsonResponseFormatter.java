package com.example.web.framework.formatter;

import com.example.web.framework.response.StandardJsonResponse;
import com.example.web.framework.util.JsonUtil;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 标准JSON响应格式格式化器.
 *
 * @author Lei
 * craeted: 2019/10/19
 */
@RestControllerAdvice
public class StandardJsonResponseFormatter implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        if (true) {
            return false;
        }

        // 类标注@RestController注解的所有接口返回值都需要特殊处理
        // 方法标注@ResponseBody注解的接口返回值需要特殊处理
        RestController restController = methodParameter.getDeclaringClass().getAnnotation(RestController.class);
        if (restController == null) {
            ResponseBody responseBody = methodParameter.getMethod().getAnnotation(ResponseBody.class);
            return responseBody != null;
        }

        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // 接口返回的是标准的JSON响应格式不用处理直接返回出去
        if (body instanceof StandardJsonResponse) {
            return body;
        }

        // 返回字符串要单独处理
        if (body instanceof String) {
            try {
                if (serverHttpResponse instanceof ServletServerHttpResponse) {
                    serverHttpResponse.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                }

                // 字符串反序列化成标准JSON格式
                JsonUtil.toObject((String) body, StandardJsonResponse.class);
                // 能反序列化说明不用再处理
                return body;
            } catch (Exception e) {
                StandardJsonResponse response = StandardJsonResponse.Builder.success(body);
                return JsonUtil.toJsonString(response);
            }
        }

        return StandardJsonResponse.Builder.success(body);
    }
}
