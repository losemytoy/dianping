package com.hmdp.config;

import com.hmdp.properties.QiniuProperties;
import com.hmdp.utils.QiniuOSSUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class OSSConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public QiniuOSSUtil qiniuOSSUtil(QiniuProperties qiniuProperties) {
        return new QiniuOSSUtil(qiniuProperties.getEndpoint(),
                qiniuProperties.getAccessKeyId(),
                qiniuProperties.getAccessKeySecret(),
                qiniuProperties.getBucketName());
    }
}
