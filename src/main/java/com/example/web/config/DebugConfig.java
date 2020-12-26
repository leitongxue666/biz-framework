package com.example.web.config;

import com.example.web.framework.interceptor.DebugInterceptor;
import com.example.web.framework.interceptor.WebInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 调试配置.
 * <p>
 * 只用当启用了<b>debug</b> Profile时才会生效.
 * </p>
 *
 * @author Lei
 * @since 1.0
 * created: 19/10/21
 */
@Profile("debug")
@Configuration
public class DebugConfig implements WebMvcConfigurer {

    private DebugInterceptor debugInterceptor;
    private WebInterceptor webInterceptor;

    public DebugConfig(DebugInterceptor debugInterceptor, WebInterceptor webInterceptor) {
        this.debugInterceptor = debugInterceptor;
        this.webInterceptor = webInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 调试所有接口
        registry.addInterceptor(debugInterceptor).addPathPatterns("/**");
        registry.addInterceptor(webInterceptor).addPathPatterns("/**");
    }
}
