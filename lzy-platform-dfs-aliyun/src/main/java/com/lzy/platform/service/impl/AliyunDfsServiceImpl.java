package com.lzy.platform.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.lzy.platform.base.exception.BusinessException;
import com.lzy.platform.constants.DfsConstants;
import com.lzy.platform.domain.LzyCloudDfsFile;
import com.lzy.platform.props.AliyunDfsProperties;
import com.lzy.platform.service.IDfsBaseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 阿里云分布式文件存储 Service 实现类
 * </p>
 *
 * @author lzy
 * @since 2023/8/2 16:44
 */
@Slf4j
@AllArgsConstructor
public class AliyunDfsServiceImpl implements IDfsBaseService {

    private final OSSClient aliyunOssClient;
    private final AliyunDfsProperties aliyunDfsProperties;

    @Override
    public String uploadToken(String bucket) {
        return null;
    }

    @Override
    public String uploadToken(String bucket, String key) {
        return null;
    }

    @Override
    public void createBucket(String bucket) {

    }

    @Override
    public LzyCloudDfsFile uploadFile(InputStream inputStream, String fileName) {
        return this.uploadFile(inputStream, aliyunDfsProperties.getBucket(), fileName);
    }

    @Override
    public LzyCloudDfsFile uploadFile(InputStream inputStream, String bucket, String fileName) {
        LzyCloudDfsFile dfsFile = new LzyCloudDfsFile();
        try {
            dfsFile.setBucket(bucket);
            dfsFile.setBucketDomain(aliyunDfsProperties.getUploadUrl());
            dfsFile.setFileUrl(aliyunDfsProperties.getAccessUrlPrefix());
            dfsFile.setEncodedFileName(fileName);

            aliyunOssClient.putObject(bucket, fileName, inputStream);
            aliyunOssClient.shutdown();
        } catch (Exception e) {
            throw new BusinessException("AliyunDfsServiceImpl uploadFile error： " + e.getMessage());
        } finally {
            if (aliyunOssClient != null) {
                aliyunOssClient.shutdown();
            }
        }
        return dfsFile;
    }

    @Override
    public String getFileUrl(String fileName) {
        return this.getFileUrl(aliyunDfsProperties.getBucket(), fileName);
    }

    @Override
    public String getFileUrl(String bucket, String fileName) {
        return this.getFileUrl(bucket, fileName, DfsConstants.DFS_FILE_DURATION, DfsConstants.DFS_FILE_DURATION_UNIT);
    }

    @Override
    public String getFileUrl(String bucket, String fileName, int duration, TimeUnit unit) {
        long millisTime  = unit.convert(duration, unit);
        Date expiration = new Date(millisTime);
        URL url = aliyunOssClient.generatePresignedUrl(bucket, fileName, expiration);
        return url.getPath();
    }

    @Override
    public OutputStream getFileObject(String fileName, OutputStream outputStream) {
        return this.getFileObject(aliyunDfsProperties.getBucket(), fileName, outputStream);
    }

    @Override
    public OutputStream getFileObject(String bucket, String fileName, OutputStream outputStream) {
        // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
        OSSObject ossObject = aliyunOssClient.getObject(bucket, fileName);
        // 读取文件内容。
        BufferedInputStream in = new BufferedInputStream(ossObject.getObjectContent());
        try {
            IOUtils.copy(in, outputStream);
        } catch (IOException e) {
            throw new BusinessException("AliyunDfsServiceImpl getFileObject error: " +e.getMessage());
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return outputStream;
    }

    @Override
    public String removeFile(String fileName) {
        return this.removeFile(aliyunDfsProperties.getBucket(),fileName);
    }

    @Override
    public String removeFile(String bucket, String fileName) {
        return this.removeFiles(bucket, Collections.singletonList(fileName));
    }

    @Override
    public String removeFiles(List<String> fileNames) {
        return this.removeFiles(aliyunDfsProperties.getBucket(), fileNames);
    }

    @Override
    public String removeFiles(String bucket, List<String> fileNames) {
        fileNames.forEach(item -> aliyunOssClient.deleteObject(bucket, item));
        return null;
    }
}
