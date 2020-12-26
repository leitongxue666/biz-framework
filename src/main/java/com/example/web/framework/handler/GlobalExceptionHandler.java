package com.example.web.framework.handler;

import com.example.web.framework.exception.AbstractBusinessException;
import com.example.web.framework.exception.handler.BusinessExceptionHandler;
import com.example.web.framework.exception.handler.StandardJsonResponseWrapExceptionHandler;
import com.example.web.framework.response.StandardJsonResponse;
import com.example.web.framework.response.status.CommonStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Comparator;
import java.util.List;

/**
 * 全局异常处理器.
 *
 * @author Lei
 * craeted: 2019/10/19
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private List<StandardJsonResponseWrapExceptionHandler> businessExceptionHandlers;

    public GlobalExceptionHandler(List<StandardJsonResponseWrapExceptionHandler> businessExceptionHandlers) {
        // 注入所有实现了接口StandardJsonResponseWrapExceptionHandler的实例
        businessExceptionHandlers.sort(Comparator.comparingInt(BusinessExceptionHandler::order));
        this.businessExceptionHandlers = businessExceptionHandlers;
    }

    @ExceptionHandler(Exception.class)
    public StandardJsonResponse handleException(Exception ex) {
        // 是否有注册的StandardJsonResponseWrapExceptionHandler实例
        if (!ObjectUtils.isEmpty(businessExceptionHandlers) && ex instanceof AbstractBusinessException) {
            AbstractBusinessException businessException = (AbstractBusinessException) ex;
            // 遍历处理器以判断异常是否有匹配的处理器
            for (StandardJsonResponseWrapExceptionHandler exceptionHandler : businessExceptionHandlers) {
                if (exceptionHandler.support(businessException)) {
                    // 匹配上了直接调用处理器处理后返回出去
                    return exceptionHandler.handle(businessException);
                }
            }
        }

        // 没有匹配到相应的处理器，默认返回服务器内部错误
        return StandardJsonResponse.Builder.failure(CommonStatus.INTERNAL);
    }

}
