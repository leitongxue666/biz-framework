package com.example.web.framework.exception;

/**
 * 业务异常，所有的非框架、系统异常都应该继承该类.
 *
 * @author Lei
 * craeted: 2019/10/19
 */
public abstract class AbstractBusinessException extends RuntimeException {
    private static final long serialVersionUID = -1449022241308870804L;

    /**
     * 获取异常响应状态码.
     */
    public abstract int getCode();

    /**
     * 获取异常响应提示消息.
     */
    public abstract String getMsg();
}
