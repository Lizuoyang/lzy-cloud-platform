package com.lzy.platform.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 阿里云分布式文件存储配置属性类
 * </p>
 *
 * @author lzy
 * @since 2023/8/2 16:42
 */
@Data
@Component
@ConfigurationProperties(prefix = "dfs.aliyun")
public class AliyunDfsProperties {
    /**
     * AccessKey
     */
    private String accessKey;

    /**
     * SecretKey
     */
    private String secretKey;

    /**
     * 区域，需要在MinIO配置服务器的物理位置，默认是us-east-1（美国东区1）,这也是亚马逊S3的默认区域。
     */
    private String region;

    /**
     * Bucket
     */
    private String bucket;

    /**
     * 公开还是私有
     */
    private Integer accessControl;

    /**
     * 上传服务器域名地址
     */
    private String uploadUrl;

    /**
     * 文件请求地址前缀
     */
    private String accessUrlPrefix;

    /**
     *  上传文件夹前缀
     */
    private String uploadDirPrefix;
}