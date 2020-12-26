package com.example.web.framework.aspect;

import com.example.web.framework.exception.AbstractBusinessException;
import com.example.web.framework.interceptor.DebugInterceptor;
import com.example.web.framework.response.DebugJsonResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 调试切面.
 * <p>
 * 用于获取异常信息，并放到debug对象中.
 * </p>
 *z
 * @author Lei
 * craeted: 2019/10/19
 */
@Component
@Aspect
public class DebugAspect {

    @Pointcut("execution(* com.example.web.framework.handler.GlobalExceptionHandler.handleException(Exception))")
    public void pointCut() {
    }

    @Around(value = "pointCut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            if (arg instanceof Exception) {
                DebugJsonResponse debugJson = getDebugJsonResponse();
                // 是否处于debug模式
                if (debugJson != null) {
                    Exception ex = (Exception) arg;
                    debugJson.setExceptionMsg(ex.getMessage());
                    StackTraceElement[] stackTrace = ex.getStackTrace();
                    if (ex instanceof AbstractBusinessException) {
                        // 业务异常记录抛出异常的方法即可
                        debugJson.setExceptionStackTrace(stackTrace[0].toString());
                    } else {
                        // 系统异常记录完整的调用栈
                        StringBuilder sb = new StringBuilder();
                        for (StackTraceElement traceElement : stackTrace) {
                            sb.append("\tat ").append(traceElement).append("\n");
                        }
                        debugJson.setExceptionStackTrace(sb.toString());
                    }
                }
            }
        }

        return pjp.proceed();

    }


    private DebugJsonResponse getDebugJsonResponse() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes!=null) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            Object debug = request.getAttribute(DebugInterceptor.REQUEST_DEBUG);
            if (debug instanceof DebugJsonResponse) {
                return (DebugJsonResponse) debug;
            }
        }

        return null;
    }

}
