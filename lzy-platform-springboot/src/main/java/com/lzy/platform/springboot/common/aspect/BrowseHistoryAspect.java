package com.lzy.platform.springboot.common.aspect;

import com.lzy.platform.base.annotation.browse.BrowseStatistics;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 浏览历史 切面类
 * </p>
 *
 * @author lzy
 * @since 2023/7/26 15:29
 */
@Log4j2
@Aspect
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class BrowseHistoryAspect {

    private final StringRedisTemplate stringRedisTemplate;
    /**
     * 切点
     */
    @Pointcut("@annotation(com.lzy.platform.base.annotation.browse.BrowseStatistics)")
    public void browseHistoryAspect() {
    }

    /**
     * 前置切入点 用于统计浏览次数
     *
     * @param joinPoint 连接点
     */
    @Before("@annotation(browseStatistics)")
    public void beforePointcut(JoinPoint joinPoint, BrowseStatistics browseStatistics) {
        final Long id = (Long)joinPoint.getArgs()[0];
        final Integer type = browseStatistics.type();
        stringRedisTemplate.opsForZSet().incrementScore("viewNum_type_" + type, id.toString(),1);
        log.info("id: " + id);
        log.info("type: " + type);
    }


}
