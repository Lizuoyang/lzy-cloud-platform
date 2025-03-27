package com.lzy.platform.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.lzy.platform.base.exception.BusinessException;
import com.lzy.platform.constants.DfsConstants;
import com.lzy.platform.domain.LzyCloudDfsFile;
import com.lzy.platform.enums.AliyunOssFileTypeEnum;
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

            // 文件大小
            long fileSize = inputStream.available();
            // 创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            // 上传的文件的长度
            metadata.setContentLength(inputStream.available());
            // 指定该Object被下载时的网页的缓存行为
            metadata.setCacheControl("no-cache");
            // 指定该Object下设置Header
            metadata.setHeader("Pragma", "no-cache");
            // 指定该Object被下载时的内容编码格式
            metadata.setContentEncoding("utf-8");
            // 文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，
            // 如果没有扩展名则填默认值application/octet-stream
            metadata.setContentType(getContentType(fileName));
            // 指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称）
            metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte");
            //上传文件到OSS时需要指定包含文件后缀在内的完整路径如ossPath="upload/2023/01/11/cake.jpg"

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

    /**
     * 通过文件名判断并获取OSS服务文件上传时文件的contentType
     *
     * @param fileName 文件名
     * @return 文件的contentType
     */
    public static String getContentType(String fileName) {
        // 文件的后缀名
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        for (AliyunOssFileTypeEnum e : AliyunOssFileTypeEnum.values()) {
            if (e.getCode().equalsIgnoreCase(fileExtension)) {
                return e.getText();
            }
        }
        // 默认返回类型
        return AliyunOssFileTypeEnum.TXT.getText();
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
