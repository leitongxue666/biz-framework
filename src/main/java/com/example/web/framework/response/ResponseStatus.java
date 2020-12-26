package com.example.web.framework.response;

/**
 * 状态码.
 * @author Lei
 * craeted: 2019/11/12
 */
public interface ResponseStatus {
    /**
     * 状态码标识.
     */
    int code();

    /**
     * 状态消息提示.
     */
    String msg();
}
