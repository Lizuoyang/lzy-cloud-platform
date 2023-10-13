package com.lzy.platform.domain;

import lombok.Data;

/**
 * <p>
 * 分布式文件存储对象
 * </p>
 *
 * @author lzy
 * @since 2023/8/2 16:25
 */
@Data
public class LzyCloudDfsFile {
    /**
     * 文件名
     */
    private String fileName;

    /**
     * UTF-8编码文件名
     */
    private String encodedFileName;

    /**
     * 存储的域名
     */
    private String bucketDomain;

    /**
     * 文件访问地址
     */
    private String fileUrl;

    /**
     * 文件key值
     */
    public String key;

    /**
     * 文件hash值
     */
    public String hash;

    /**
     * 分布式存储bucket
     */
    public String bucket;

    /**
     * 文件大小
     */
    private long fileSize;
}
