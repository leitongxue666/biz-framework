package com.example.web.framework.interceptor;

import com.example.web.framework.util.CryptoUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Lei
 * craeted: 2020/4/8
 */
@Component
public class WebInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        MDC.put("REQUEST_ID", CryptoUtils.randomString(8));
        return true;
    }
}
