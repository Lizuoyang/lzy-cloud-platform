package com.lzy.platform.sms.domain;

import lombok.Data;

import java.util.Map;

/**
 * 短信内容
 *
 * @author lizuoyang
 * @date 2025/03/10
 */
@Data
public class SmsData {

    /**
     * 短信模板
     */
    private String templateId;

    /**
     * 参数列表
     */
    private Map<String, String> params;
}
