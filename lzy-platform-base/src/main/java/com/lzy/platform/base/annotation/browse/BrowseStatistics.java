package com.lzy.platform.base.annotation.browse;

import java.lang.annotation.*;


/**
 * 记录浏览次数
 *
 * @author lizuoyang
 * @date 2023/02/10
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BrowseStatistics {

    int type() default 0;
}
