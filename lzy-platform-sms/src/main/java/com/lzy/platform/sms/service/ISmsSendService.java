package com.lzy.platform.sms.service;


import com.lzy.platform.base.result.Result;
import com.lzy.platform.sms.domain.SmsData;

import java.util.Collection;


/**
 * 短信发送接口
 * @author lizuoyang
 * @date 2025/03/27
 */
public interface ISmsSendService {

    /**
     * 发送单个短信
     * @param smsData
     * @param phoneNumber
     * @return
     */
    Result<?> sendSms(SmsData smsData, String phoneNumber);

    /**
     * 群发发送短信
     * @param smsData
     * @param phoneNumbers
     * @return
     */
    Result<?> sendSms(SmsData smsData, Collection<String> phoneNumbers);

}
