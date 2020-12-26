package com.example.web.framework.formatter;

import com.example.web.framework.interceptor.DebugInterceptor;
import com.example.web.framework.response.DebugJsonResponse;
import com.example.web.framework.response.StandardJsonResponse;
import com.example.web.framework.util.JsonUtil;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static com.example.web.framework.interceptor.DebugInterceptor.REQUEST_START_TIME;

/**
 * 调试JSON响应格式化器.
 *
 * @author Lei
 * @since 1.0
 * created: 19/10/21
 */
@RestControllerAdvice
public class DebugJsonResponseFormatter extends StandardJsonResponseFormatter {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        Object debug = getHttpServletRequest().getAttribute(DebugInterceptor.REQUEST_DEBUG);
        return debug instanceof DebugJsonResponse;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        Object response = super.beforeBodyWrite(body, methodParameter, mediaType, aClass, serverHttpRequest, serverHttpResponse);
        StandardJsonResponse standardJsonResponse;
        boolean returnString = response instanceof String;
        if (returnString) {
            standardJsonResponse = JsonUtil.toObject((String) response, StandardJsonResponse.class);
        } else {
            standardJsonResponse = (StandardJsonResponse) response;
        }

        // 填充必要字段值
        DebugJsonResponse debug = (DebugJsonResponse) getHttpServletRequest().getAttribute(DebugInterceptor.REQUEST_DEBUG);
        debug.setCode(standardJsonResponse.getCode());
        debug.setMsg(standardJsonResponse.getMsg());
        debug.setData(standardJsonResponse.getData());

        // 计算接口耗时
        long start = (long) getHttpServletRequest().getAttribute(REQUEST_START_TIME);
        long costTime = System.currentTimeMillis() - start;
        debug.setCostTime(costTime);

        return returnString ? JsonUtil.toJsonString(debug) : debug;
    }

    private HttpServletRequest getHttpServletRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        return ((ServletRequestAttributes) requestAttributes).getRequest();
    }
}
