package com.lzy.platform.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

/**
 * RestTemplate 自动配置类
 * @author lizuoyang
 * @date 2025/03/15
 */
@Configuration
public class RestTemplateConfig {

    /**
     * 默认超时时间（毫秒）
     */
    private static final int DEFAULT_CONNECT_TIMEOUT = 5000;
    private static final int DEFAULT_READ_TIMEOUT = 10000;

    /**
     * 配置RestTemplate的请求工厂
     * @return ClientHttpRequestFactory
     */
    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        // 设置连接超时时间
        factory.setConnectTimeout(DEFAULT_CONNECT_TIMEOUT);
        // 设置读取超时时间
        factory.setReadTimeout(DEFAULT_READ_TIMEOUT);
        return factory;
    }

    /**
     * 配置默认的RestTemplate
     * @param factory 请求工厂
     * @return RestTemplate
     */
    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        RestTemplate restTemplate = new RestTemplate(factory);
        
        // 设置UTF-8字符集
        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        restTemplate.getMessageConverters().add(0, stringConverter);
        
        // 设置错误处理器
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
        
        return restTemplate;
    }

    /**
     * 配置SSL信任的RestTemplate
     * 这个Bean用于处理https请求，忽略SSL证书验证
     * @return RestTemplate
     */
    @Bean
    public RestTemplate sslRestTemplate() {
        RestTemplate restTemplate = new RestTemplate(new SkipSslVerificationRequestFactory());
        
        // 设置UTF-8字符集
        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        restTemplate.getMessageConverters().add(0, stringConverter);
        
        // 设置错误处理器
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
        
        return restTemplate;
    }

    /**
     * 跳过SSL验证的请求工厂
     */
    public static class SkipSslVerificationRequestFactory extends SimpleClientHttpRequestFactory {
        
        public SkipSslVerificationRequestFactory() {
            super();
            setConnectTimeout(DEFAULT_CONNECT_TIMEOUT);
            setReadTimeout(DEFAULT_READ_TIMEOUT);
        }
        
        @Override
        protected void prepareConnection(java.net.HttpURLConnection connection, String httpMethod) throws java.io.IOException {
            if (connection instanceof javax.net.ssl.HttpsURLConnection) {
                prepareHttpsConnection((javax.net.ssl.HttpsURLConnection) connection);
            }
            super.prepareConnection(connection, httpMethod);
        }
        
        private void prepareHttpsConnection(javax.net.ssl.HttpsURLConnection connection) {
            try {
                javax.net.ssl.SSLContext ctx = javax.net.ssl.SSLContext.getInstance("TLS");
                ctx.init(null, new javax.net.ssl.TrustManager[] { new TrustAllManager() }, new java.security.SecureRandom());
                connection.setSSLSocketFactory(ctx.getSocketFactory());
                connection.setHostnameVerifier((hostname, session) -> true);
            } catch (Exception e) {
                throw new RuntimeException("Failed to initialize SSL context", e);
            }
        }
        
        private static class TrustAllManager implements javax.net.ssl.X509TrustManager {
            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[0];
            }

            @Override
            public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                // 信任所有客户端证书
            }

            @Override
            public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                // 信任所有服务器证书
            }
        }
    }
} 