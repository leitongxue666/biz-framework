package com.example.web.framework.interceptor;

import com.example.web.framework.response.DebugJsonResponse;
import com.example.web.framework.util.CryptoUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 调试拦截器
 *
 * @author Lei
 * craeted: 2019/10/19
 */
@Component
public class DebugInterceptor extends HandlerInterceptorAdapter {
    public static final String REQUEST_DEBUG = "__debug__";
    public static final String REQUEST_START_TIME = "__debug_start_time_";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 初始化调试对象
        DebugJsonResponse debugJson = new DebugJsonResponse();
        request.setAttribute(REQUEST_DEBUG, debugJson);
        request.setAttribute(REQUEST_START_TIME, System.currentTimeMillis());
        return true;
    }
}
