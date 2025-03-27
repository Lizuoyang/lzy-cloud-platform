package com.lzy.platform.sms.aliyun.impl;

import cn.hutool.core.util.StrUtil;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.utils.StringUtils;
import com.lzy.platform.base.enums.ResultCodeEnum;
import com.lzy.platform.base.result.Result;
import com.lzy.platform.base.utils.JsonUtils;
import com.lzy.platform.sms.aliyun.props.AliyunSmsProperties;
import com.lzy.platform.sms.domain.SmsData;
import com.lzy.platform.sms.service.ISmsSendService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;


/**
 * 阿里云短信发送
 * @author lizuoyang
 * @date 2025/03/10
 */
@Slf4j
@AllArgsConstructor
public class AliyunSmsSendServiceImpl implements ISmsSendService {

    private static final String SUCCESSCODE = "OK";

    private final AliyunSmsProperties properties;

    private final IAcsClient acsClient;

    @Override
    public Result<?> sendSms(SmsData smsData, String phoneNumber) {
        SendSmsRequest request = new SendSmsRequest();
        request.setSysMethod(MethodType.POST);
        request.setPhoneNumbers(phoneNumber);
        request.setSignName(properties.getSignName());
        request.setTemplateCode(smsData.getTemplateId());
        request.setTemplateParam(JsonUtils.mapToJson(smsData.getParams()));
        try {
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            if (null != sendSmsResponse && !StringUtils.isEmpty(sendSmsResponse.getCode())) {
                if (AliyunSmsSendServiceImpl.SUCCESSCODE.equals(sendSmsResponse.getCode())) {
                    return Result.success(sendSmsResponse.getMessage());
                } else {
                    log.error("Send Aliyun Sms Fail: [code={}, message={}]", sendSmsResponse.getCode(), sendSmsResponse.getMessage());
                    return Result.error(ResultCodeEnum.SMS_SEND_FAILED, sendSmsResponse.getMessage());
                }
            }
        } catch (Exception e) {
            log.error("Send Aliyun Sms Fail: {}", e);
        }
        return Result.error(ResultCodeEnum.SMS_SEND_ERROR);
    }

    @Override
    public Result<?> sendSms(SmsData smsData, Collection<String> phoneNumbers) {
        SendSmsRequest request = new SendSmsRequest();
        request.setSysMethod(MethodType.POST);
        request.setPhoneNumbers(StrUtil.join(",", phoneNumbers));
        request.setSignName(properties.getSignName());
        request.setTemplateCode(smsData.getTemplateId());
        request.setTemplateParam(JsonUtils.mapToJson(smsData.getParams()));
        try {
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            if (null != sendSmsResponse && !StringUtils.isEmpty(sendSmsResponse.getCode())) {
                if (AliyunSmsSendServiceImpl.SUCCESSCODE.equals(sendSmsResponse.getCode())) {
                    return Result.success(sendSmsResponse.getMessage());
                } else {
                    log.error("Send Aliyun Sms Fail: [code={}, message={}]", sendSmsResponse.getCode(), sendSmsResponse.getMessage());
                    return Result.error(ResultCodeEnum.SMS_SEND_FAILED, sendSmsResponse.getMessage());
                }
            }
        } catch (Exception e) {
            log.error("Send Aliyun Sms Fail: {}", e);
        }
        return Result.error(ResultCodeEnum.SMS_SEND_ERROR);
    }


}
