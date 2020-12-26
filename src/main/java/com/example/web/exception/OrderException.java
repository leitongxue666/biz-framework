package com.example.web.exception;

import com.example.web.framework.exception.AbstractBusinessException;
import com.example.web.framework.exception.handler.BusinessExceptionHandler;

/**
 * @author Lei
 * craeted: 2019/11/12
 */
public class OrderException extends AbstractBusinessException {
    @Override
    public int getCode() {
        return 0;
    }

    @Override
    public String getMsg() {
        return null;
    }
}
