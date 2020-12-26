package com.example.web.framework.exception.handler;

import com.example.web.framework.exception.AbstractBusinessException;

/**
 * 业务异常处理器.
 *
 * @author Lei
 * @since 1.0
 * created: 19/10/21
 */
public interface BusinessExceptionHandler<R> {
    /**
     * 该处理器是否支持处理捕获的异常.
     *
     * @param ex 异常对象
     * @return 返回true表示支持处理
     */
    boolean support(AbstractBusinessException ex);

    /**
     * 处理异常.
     *
     * @param ex 异常对象
     * @return 返回处理结果
     */
    R handle(AbstractBusinessException ex);

    /**
     * 在所有注册的处理器中的序号.
     */
    default int order() {
        return 0;
    }
}
