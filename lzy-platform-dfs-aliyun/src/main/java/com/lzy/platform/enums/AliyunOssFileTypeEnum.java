package com.lzy.platform.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 阿里云文件上传文件类型枚举
 * @author lizuoyang
 * @date 2025/03/07
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum AliyunOssFileTypeEnum {
    /**
     * image/bmp    
     */
    BMP(".bmp","image/bmp"),
    /**
     * image/gif
     */
    GIF(".gif","image/gif"),
    /**
     * image/jpeg
     */
    JPEG(".jpeg","image/jpeg"),
    /**
     * image/png
     */
    PNG(".png","image/png"),
    /**
     * text/html
     */
    HTML(".html","text/html"),
    /**
     * text/xml
     */
    XML(".xml","text/xml"),
    /**
     * application/octet-stream
     */
    TXT(".txt","application/octet-stream"),
    /**
     * application/sql
     */
    SQL(".sql","application/sql"),
    /**
     * application/vnd.visio
     */
    VSD(".vsd","application/vnd.visio"),
    /**
     * application/pdf
     */
    PDF(".pdf","application/pdf"),
    /**
     * application/vnd.ms-powerpoint
     */
    PPT(".ppt","application/vnd.ms-powerpoint"),
    /**
     * application/vnd.ms-powerpoint
     */
    PPTX(".pptx","application/vnd.ms-powerpoint"),
    /**
     * application/msword
     */
    DOC(".doc","application/msword"),
    /**
     * application/msword
     */
    DOCX(".docx","application/msword"),
    /**
     * application/vnd.ms-excel
     */
    XLS(".xls","application/vnd.ms-excel"),
    /**
     * application/vnd.ms-excel
     */
    XLSX(".xlsx","application/vnd.ms-excel"),
    /**
     * application/vnd.ms-excel
     */
    CSV(".csv","application/vnd.ms-excel");


    /**
     * 类型code
     */
    private String code;


    /**
     * 类型
     */
    private String text;

}
