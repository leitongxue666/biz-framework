package com.example.web;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author lei
 */
public class RequestParamBuilder {
        private Map<String, Object> param;

        private RequestParamBuilder() {
        }

        public RequestParamBuilder(Map<String, Object> param) {
            this.param = param;
        }

        public static RequestParamBuilder builder() {
            return new RequestParamBuilder(new LinkedHashMap<>(8));
        }

        public RequestParamBuilder add(String name, Object value) {
            param.put(name, value);
            return this;
        }

        public RequestParamBuilder addIfValueNotNull(String name, Object value) {
            if (value != null) {
                return add(name, value);
            }
            return this;
        }

        public String buildJsonString() {
            return JsonUtil.toJsonString(param);
        }

        public String buildQueryParamString() {
            StringBuilder paramStr = new StringBuilder();
            param.forEach((name, val) -> {
                if (paramStr.length() != 0) {
                    paramStr.append('&');
                }
                paramStr.append(name).append('=').append(val);
            });
            return paramStr.toString();
        }
    }