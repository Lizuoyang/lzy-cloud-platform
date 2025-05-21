package com.lzy.platform.base.utils;

import cn.hutool.http.HttpException;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.lzy.platform.base.result.HttpResultRes;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * 基于hutool http工具类二次封装
 * @author lizuoyang
 * @date 2025/03/21
 */
@Slf4j
public class LzyHttpUtils {
    /**
     * 超时毫秒数。http的连接与读取
     */
    private static final int TIME_OUT_MILLISECONDS = 60000;

    /**
     * Content-Type
     */
    private static final String CONTENT_TYPE_JSON = "application/json; charset=utf-8";

    private static final String CONTENT_TYPE_FORM = "multipart/form-data";

    /**
     * GET请求
     */
    public static HttpResultRes get(String url) {
        return get(url, null);
    }

    public static HttpResultRes get(String url, Map<String, String> headerMap) {
        HttpResultRes httpResultRes = new HttpResultRes();
        try {
            HttpResponse response = HttpRequest
                    .get(url)
                    .timeout(TIME_OUT_MILLISECONDS)
                    .addHeaders(headerMap)
                    .execute();

            if (!response.isOk()) {
                log.error("HttpUtils get 响应失败. url={}，headerMap={}, response={}", url, headerMap, response.toString());
                return httpResultRes.buildError();
            }

            return httpResultRes.buildSuccess(response.body());
        } catch (HttpException e) {
            log.error("HttpUtils get HttpException异常. url={}，headerMap={}", url, headerMap, e);
            return httpResultRes.buildError(e.getMessage());
        } catch (Exception e) {
            log.error("HttpUtils get Exception异常. url={}，headerMap={}", url, headerMap, e);
            return httpResultRes.buildError(e.getMessage());
        }
    }

    public static HttpResultRes post(String url, Object postBody) {
        return post(url, postBody, null);
    }

    /**
     * post 请求
     *
     * @param url       请求链接
     * @param postBody  请求对象 or 请求json字符串
     * @param headerMap 请求头map
     *
     * @return HttpResultRes
     */
    public static HttpResultRes post(String url, Object postBody, Map<String, String> headerMap) {
        HttpResultRes httpResultRes = new HttpResultRes();

        try {
            HttpRequest httpRequest = HttpRequest
                    .post(url)
                    .timeout(TIME_OUT_MILLISECONDS)
                    .addHeaders(headerMap);

            if (postBody instanceof byte[]) {
                httpRequest.body((byte[]) postBody);
            } else if (postBody instanceof String) {
                httpRequest.body((String) postBody);
            } else {
                httpRequest.body(JSONUtil.toJsonStr(postBody), CONTENT_TYPE_JSON);
            }

            HttpResponse response = httpRequest.execute();

            if (!response.isOk()) {
                log.error("HttpUtils post 响应失败. url={}，postBody={}, headerMap={}, response={}", url, postBody,
                        headerMap, response.toString());
                return httpResultRes.buildError();
            }

            return httpResultRes.buildSuccess(response.body());
        } catch (HttpException e) {
            log.error("HttpUtils post HttpException异常. url={}，postBody={}, headerMap={}", url, postBody, headerMap, e);
            return httpResultRes.buildError(e.getMessage());
        } catch (Exception e) {
            log.error("HttpUtils post Exception异常. url={}，postBody={},headerMap={}", url, postBody, headerMap, e);
            return httpResultRes.buildError(e.getMessage());
        }
    }

    public static HttpResultRes postByForm(String url, Object postBody, Map<String, String> headerMap) {
        HttpResultRes httpResultRes = new HttpResultRes();

        String body;
        if (postBody instanceof String) {
            body = (String) postBody;
        } else {
            body = JSONUtil.toJsonStr(postBody);
        }

        try {
            HttpResponse response = HttpRequest
                    .post(url)
                    .body(body, CONTENT_TYPE_FORM)
                    .timeout(TIME_OUT_MILLISECONDS)
                    .addHeaders(headerMap)
                    .execute();

            if (!response.isOk()) {
                log.error("HttpUtils post 响应失败. url={}，postBody={}, headerMap={}, response={}", url, postBody,
                        headerMap, response.toString());
                return httpResultRes.buildError();
            }

            return httpResultRes.buildSuccess(response.body());
        } catch (HttpException e) {
            log.error("HttpUtils post HttpException异常. url={}，postBody={}, headerMap={}", url, postBody, headerMap, e);
            return httpResultRes.buildError(e.getMessage());
        } catch (Exception e) {
            log.error("HttpUtils post Exception异常. url={}，postBody={},headerMap={}", url, postBody, headerMap, e);
            return httpResultRes.buildError(e.getMessage());
        }
    }

    /**
     * put 请求, 无header
     *
     * @param url     链接
     * @param putBody 请求对象 or 请求json字符串
     *
     * @return HttpResultRes
     */
    public static HttpResultRes put(String url, Object putBody) {
        return put(url, putBody, null);
    }

    /**
     * put 请求
     *
     * @param url       请求链接
     * @param putBody   请求对象 or 请求json字符串
     * @param headerMap 请求头map
     *
     * @return HttpResultRes
     */
    public static HttpResultRes put(String url, Object putBody, Map<String, String> headerMap) {
        HttpResultRes httpResultRes = new HttpResultRes();

        try {
            HttpRequest httpRequest = HttpRequest
                    .put(url)
                    .timeout(TIME_OUT_MILLISECONDS)
                    .addHeaders(headerMap);

            if (putBody instanceof byte[]) {
                httpRequest.body((byte[]) putBody);
            } else if (putBody instanceof String) {
                httpRequest.body((String) putBody);
            } else {
                httpRequest.body(JSONUtil.toJsonStr(putBody), CONTENT_TYPE_JSON);
            }

            HttpResponse response = httpRequest.execute();

            if (!response.isOk()) {
                log.error("HttpUtil put 响应失败. url={}，putBody={}, headerMap={}, response={}", url, putBody,
                        headerMap, response.toString());
                return httpResultRes.buildError();
            }

            return httpResultRes.buildSuccess(response.body());
        } catch (HttpException e) {
            log.error("HttpUtil put HttpException异常. url={}，putBody={}, headerMap={}", url, putBody, headerMap, e);
            return httpResultRes.buildError(e.getMessage());
        } catch (Exception e) {
            log.error("HttpUtil put Exception异常. url={}，putBody={},headerMap={}", url, putBody, headerMap, e);
            return httpResultRes.buildError(e.getMessage());
        }
    }
}
