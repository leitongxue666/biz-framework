package com.example.web.framework.exception.handler;

import com.example.web.framework.response.StandardJsonResponse;

/**
 * 标准JSON响应格式封装异常处理器.
 * <p>
 * 将异常信息封装到标准的JSON响应体中.
 * </p>
 *
 * @author Lei
 * @since 1.0
 * created: 19/10/21
 */
public interface StandardJsonResponseWrapExceptionHandler extends BusinessExceptionHandler<StandardJsonResponse> {
}
