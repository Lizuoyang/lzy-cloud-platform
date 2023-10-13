package com.lzy.platform.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 分布式文件存储枚举类，声明文件管理渠道
 * </p>
 *
 * @author lzy
 * @since 2023/8/2 16:27
 */
@Getter
@AllArgsConstructor
public enum DfsEnum {
    /**
     * local
     */
    LOCAL("local", "本地存储"),

    /**
     * minio
     */
    MINIO("minio", "minio存储"),

    /**
     * qiniu
     */
    QI_NIU("qiniu", "七牛云存储"),

    /**
     * aliyun
     */
    ALI_YUN("aliyun", "阿里云存储"),

    /**
     * tencent
     */
    TENCENT("tencent", "腾讯云存储"),
    ;

    /**
     * 存储渠道码
     */
    final String channelCode;


    /**
     * 存储渠道
     */
    final String channelName;

    /**
     * 匹配枚举值
     *
     * @param channelCode 存储渠道码
     * @return DfsEnum
     */
    public static DfsEnum of(String channelCode) {
        if (channelCode == null) {
            return null;
        }
        DfsEnum[] values = DfsEnum.values();
        for (DfsEnum dfsEnum : values) {
            if (dfsEnum.channelCode.equals(channelCode)) {
                return dfsEnum;
            }
        }
        return null;
    }
}
