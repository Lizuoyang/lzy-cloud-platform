package com.lzy.platform.base.constant;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 2428
 * @ClassName: LzyCloudConstant
 * @Description: 常量类
 * @date 2022/06/21
 */
public class LzyCloudConstant {

    /**
     * 根节点id
     */
    public static final Long PARENT_ID = 0L;

    /**
     * 启用
     */
    public static final int ENABLE = 1;

    /**
     * 禁用
     */
    public static final int DISABLE = 0;

    /**
     * 结果返回0
     */
    public static final int COUNT_ZERO = 0;

    /**
     * 设置查询不分页
     */
    public static final long NO_PAGE = -1;

    /**
     * ZERO_LONG
     */
    public static final Long ZERO_LONG = 0L;

    /**
     * ONE_LONG
     */
    public static final Long ONE_LONG = 1L;

    /**
     * TWO_LONG
     */
    public static final Long TWO_LONG = 2L;

    /**
     * THREE_LONG
     */
    public static final Long THREE_LONG = 3L;

    /**
     * FOUR_LONG
     */
    public static final Long FOUR_LONG = 4L;

    /**
     * FIVE_LONG
     */
    public static final Long FIVE_LONG = 5L;

    /**
     * 数组取值
     */
    public class Number {

        public static final int ZERO = 0;

        public static final int ONE = 1;

        public static final int TWO = 2;

        public static final int THREE = 3;

        public static final int FOUR = 4;

        public static final int FIVE = 5;

        public static final int FIFTEEN = 15;

        public static final int HUNDRED = 100;

        public static final int THOUSAND = 1000;

    }

    /**
     * 用户状态
     */
    public class UserStatus {

        public static final int DISABLE = 0;

        public static final int ENABLE = 1;

        public static final int NOT_ACTIVE = 2;

    }

    /**
     * 地址
     */
    public class Address {

        public static final int PROVINCE = 0;

        public static final int CITY = 1;

        public static final int AREA = 2;

    }

    /**
     * 超时时间类型
     */
    public class ExpTimeType {

        public static final String WEB = "web";

        public static final String APP = "app";

    }

    /**
     * 允许上传的的图片文件格式，需要与 WebSecurityConfig对应
     */
    public static final Set<String> ALLOW_UPLOAD_IMG_SUFFIX = new HashSet<>();
    static{
        ALLOW_UPLOAD_IMG_SUFFIX.add("jpg");
        ALLOW_UPLOAD_IMG_SUFFIX.add("png");
        ALLOW_UPLOAD_IMG_SUFFIX.add("jpeg");
        ALLOW_UPLOAD_IMG_SUFFIX.add("gif");
        ALLOW_UPLOAD_IMG_SUFFIX.add("mp4");
    }
}
