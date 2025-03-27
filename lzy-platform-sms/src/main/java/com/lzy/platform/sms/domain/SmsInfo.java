package com.lzy.platform.sms.domain;

import lombok.Data;

import java.util.Collection;

/**
 * 短信请求内容
 *
 * @author lizuoyang
 * @date 2025/03/10
 */
@Data
public class SmsInfo {

    /**
     * 短信内容
     */
    private SmsData smaData;

    /**
     * 手机号码列表
     */
    private Collection<String> phoneNumbers;
}
