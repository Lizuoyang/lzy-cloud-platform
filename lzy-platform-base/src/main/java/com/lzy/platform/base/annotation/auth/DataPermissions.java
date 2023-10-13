package com.lzy.platform.base.annotation.auth;

import java.lang.annotation.*;


/**
 * 数据权限过滤
 *
 * @author lizuoyang
 * @date 2023/07/17
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataPermissions {

    /**
     * 需要做数据权限主表
     */
    DataPermission[] value();
}
