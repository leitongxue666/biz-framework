package com.example.web.framework.response.status;

import com.example.web.framework.response.ResponseStatus;

/**
 * 通用响应状态码.
 *<p>
 *     与具体业务无关的响应状态码
 *</p>
 * @author Lei
 * craeted: 2019/10/19
 */
public enum CommonStatus implements ResponseStatus {

    SUCCESS(1200, "成功"),

    INVALID_PARAMETER(1400, "无效的参数"),

    DATA_NOT_FOUND(1404, "找不到数据"),

    INTERNAL(1500, "服务器内部错误");

    private final int code;
    private final String msg;

    CommonStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String msg() {
        return msg;
    }
}
