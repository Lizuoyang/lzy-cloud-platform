package com.lzy.platform.base.result;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * http请求结果响应
 * @author lizuoyang
 * @date 2025/03/21
 */
@Data
@ApiModel(description = "http请求结果响应")
public class HttpResultRes {
    /**
     * 响应成功 or 失败
     */
    private Boolean success;

    /**
     * 信息提示
     */
    private String message;

    /**
     * http响应的body
     */
    private String body;

    public HttpResultRes buildSuccess(String body) {
        return buildSuccess(body, "响应成功");
    }

    public HttpResultRes buildSuccess(String body, String message) {
        this.success = true;
        this.message = message;
        this.body = body;
        return this;
    }

    public HttpResultRes buildError() {
        return buildError("响应失败");
    }

    public HttpResultRes buildError(String message) {
        this.success = false;
        this.message = message;
        this.body = null;
        return this;
    }
}
