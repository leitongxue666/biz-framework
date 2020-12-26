package com.example.web.exception;

import com.example.web.framework.exception.AbstractBusinessException;
import com.example.web.framework.response.status.CommonStatus;

/**
 * 数据库无数据异常.
 *
 * @author Lei
 * craeted: 2019/10/19
 */
public class DataNotFoundException extends AbstractBusinessException {
    private static final long serialVersionUID = 8554041418756997113L;
    private static final CommonStatus STATUS = CommonStatus.DATA_NOT_FOUND;

    @Override
    public int getCode() {
        return STATUS.code();
    }

    @Override
    public String getMsg() {
        return STATUS.msg();
    }
}
