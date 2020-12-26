package com.example.web.exception;

import com.example.web.framework.exception.AbstractBusinessException;
import com.example.web.framework.response.status.CommonStatus;

/**
 * 参数异常.
 *
 * @author Lei
 * craeted: 2019/10/19
 */
public class ParameterException extends AbstractBusinessException {
    private static final long serialVersionUID = 3486858131517437399L;
    private static final CommonStatus STATUS = CommonStatus.INVALID_PARAMETER;

    @Override
    public int getCode() {
        return STATUS.code();
    }

    @Override
    public String getMsg() {
        return STATUS.msg();
    }
}
