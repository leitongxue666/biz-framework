package com.example.web.framework.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 加入调试信息的JSON响应数据.
 *
 * @author Lei
 * craeted: 2019/10/19
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DebugJsonResponse extends StandardJsonResponse {
    /**
     * 异常消息.
     */
    @JsonProperty("_ex_msg")
    private String exceptionMsg;

    /**
     * 异常调用栈.
     */
    @JsonProperty("_ex_stack")
    private String exceptionStackTrace;

    /**
     * 接口耗时.
     */
    @JsonProperty("_cost_time")
    private Long costTime;
}
