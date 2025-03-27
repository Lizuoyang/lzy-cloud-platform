package com.lzy.platform.sms.aliyun.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 阿里云短信自动配置类
 * @author lizuoyang
 * @date 2025/03/10
 */
@Data
@Component
@ConfigurationProperties(prefix = "sms.aliyun")
public class AliyunSmsProperties {

    /**
     * product
     */
    private String product = "Dysmsapi";

    /**
     * domain
     */
    private String domain = "dysmsapi.aliyuncs.com";

    /**
     * regionId
     */
    private String regionId = "cn-hangzhou";

    /**
     * accessKeyId
     */
    private String accessKeyId;

    /**
     * accessKeySecret
     */
    private String accessKeySecret;

    /**
     * 短信签名
     */
    private String signName;
}
