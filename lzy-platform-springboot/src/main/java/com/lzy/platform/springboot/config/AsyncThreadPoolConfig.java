package com.lzy.platform.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 异步线程池配置
 * @author lizuoyang
 * @date 2024/12/04
 */
@Configuration
@EnableAsync
public class AsyncThreadPoolConfig implements AsyncConfigurer {

    private static final int MAX_POOL_SIZE = 16;
    private static final int CORE_POOL_SIZE = 8;
    private static final int TASK_NUM = 80;
    private static final int ACTIVE_TIME = 60;

    @Bean("asyncTaskExecutor")
    public AsyncTaskExecutor asyncTaskExecutor() {
        ThreadPoolTaskExecutor asyncTaskExecutor = new ThreadPoolTaskExecutor();
        asyncTaskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
        asyncTaskExecutor.setCorePoolSize(CORE_POOL_SIZE);
        asyncTaskExecutor.setQueueCapacity(TASK_NUM);
        asyncTaskExecutor.setKeepAliveSeconds(ACTIVE_TIME);
        asyncTaskExecutor.setThreadNamePrefix("Async-");
        asyncTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        asyncTaskExecutor.initialize();
        System.out.println("ThreadPoolTaskExecutor initialized with name prefix: " + asyncTaskExecutor.getThreadNamePrefix());
        return asyncTaskExecutor;
    }
}
