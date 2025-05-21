package com.lzy.platform.springboot.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * springboot打印信息配置
 * @author lizuoyang
 * @date 2024/12/04
 */
@Configuration
public class PrintApplicationInfoConfig {
    @Bean
    public CommandLineRunner printApplicationInfo(Environment environment, ConfigurableApplicationContext applicationContext) {
        return args -> {
            String successMsg = "  ____   _                _                                                           __         _  _ \n"
                    + " / ___| | |_  __ _  _ __ | |_   _   _  _ __    ___  _   _   ___  ___  ___  ___  ___  / _| _   _ | || |\n"
                    + " \\___ \\ | __|/ _` || '__|| __| | | | || '_ \\  / __|| | | | / __|/ __|/ _ \\/ __|/ __|| |_ | | | || || |\n"
                    + "  ___) || |_| (_| || |   | |_  | |_| || |_) | \\__ \\| |_| || (__| (__|  __/\\__ \\\\__ \\|  _|| |_| || ||_|\n"
                    + " |____/  \\__|\\__,_||_|    \\__|  \\__,_|| .__/  |___/ \\__,_| \\___|\\___|\\___||___/|___/|_|   \\__,_||_|(_)\n"
                    + "                                      |_|                                                             ";

            // 获取运行环境
            String[] activeProfiles = environment.getActiveProfiles();
            String activeProfile = activeProfiles.length > 0 ? Arrays.toString(activeProfiles) : "default";

            // 获取端口号
            String port = environment.getProperty("server.port", "8080");
            String contextPath = environment.getProperty("server.servlet.context-path", "");

            // 获取主机地址
            String host;
            try {
                host = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                host = "localhost";
            }

            // 构建Knife4j地址
            String knife4jUrl = "http://" + host + ":" + port + contextPath + "/doc.html";

            // 打印启动信息
            System.out.println(successMsg);
            System.out.println("----------------------------------------------------------");
            System.out.println("Application is running! Access URLs:");
            System.out.println("环境(Environment): \t" + activeProfile);
            System.out.println("本地(Local): \t\thttp://localhost:" + port + contextPath);
            System.out.println("外部(External): \thttp://" + host + ":" + port + contextPath);
            System.out.println("API文档(Knife4j): \t" + knife4jUrl);
            System.out.println("----------------------------------------------------------");
        };
    }
}
