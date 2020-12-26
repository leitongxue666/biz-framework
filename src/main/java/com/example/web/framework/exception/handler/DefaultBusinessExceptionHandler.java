package com.example.web.framework.exception.handler;

import com.example.web.framework.exception.AbstractBusinessException;
import com.example.web.framework.response.StandardJsonResponse;
import org.springframework.stereotype.Component;

/**
 * 默认的业务异常处理器.
 * <p>
 * 大部分继承了{@link com.example.web.framework.exception.AbstractBusinessException} 都不必手动编写异常处理器.
 * </p>
 *
 * @author Lei
 * @since 1.0
 * created: 19/10/21
 */
@Component
public class DefaultBusinessExceptionHandler implements StandardJsonResponseWrapExceptionHandler {

    @Override
    public boolean support(AbstractBusinessException ex) {
        return true;
    }

    @Override
    public StandardJsonResponse handle(AbstractBusinessException ex) {
        return StandardJsonResponse.Builder.failure(ex.getCode(), ex.getMsg());
    }

    @Override
    public int order() {
        // 默认的异常处理器需要排到尾部
        return -1;
    }
}
