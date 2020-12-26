package com.example.web.framework.response;

import com.example.web.framework.response.status.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JSON响应格式声明.
 *
 * @author Lei
 * craeted: 2019/10/19
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StandardJsonResponse {
    /**
     * 状态码.
     */
    private Integer code;

    /**
     * 响应消息.
     */
    private String msg;

    /**
     * 返回数据.
     */
    private Object data;

    public static class Builder {
        /**
         * 成功处理无数据返回.
         */
        public static StandardJsonResponse success() {
            return success(null);
        }

        /**
         * 成功处理且有数据返回.
         * @param data 返回数据
         */
        public static StandardJsonResponse success(Object data) {
            CommonStatus success = CommonStatus.SUCCESS;
            return new StandardJsonResponse(success.code(), success.msg(), data);
        }

        /**
         * 处理失败且自定义失败提示消息.
         * @param msg 失败提示消息.
         */
        public static StandardJsonResponse failure(String msg) {
            return new StandardJsonResponse(CommonStatus.INTERNAL.code(), msg, null);
        }

        /**
         * 处理失败且自定义失败状态.
         */
        public static StandardJsonResponse failure(ResponseStatus responseStatus) {
            return new StandardJsonResponse(responseStatus.code(), responseStatus.msg(), null);
        }

        /**
         * 处理失败且自定义失败状态码和提示消息.
         * @param code 状态数字标识
         * @param msg 失败提示消息
         */
        public static StandardJsonResponse failure(Integer code, String msg) {
            return new StandardJsonResponse(code, msg, null);
        }

    }
}
