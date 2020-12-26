package com.example.web.response.status;

import com.example.web.framework.response.ResponseStatus;

/**
 * 订单相关状态码
 *
 * @author Lei
 * craeted: 2019/11/12
 */
public enum OrderStatus implements ResponseStatus {

    PAYMENT_FAILURE(2001, "订单支付失败"),

    UNDERSTOCK(2002, "库存不足");

    private final int code;
    private final String msg;

    OrderStatus(int code, String msg) {
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
