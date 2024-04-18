package com.tyut.employee.config;

import io.minio.MinioClient;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xh
 * @Date 2022/5/28
 */
@Configuration
@ConfigurationProperties(prefix = "oss")  // 1
@Setter // 2
public class MinIOConfiguration {
    private String endpoint;  // 3 不是静态 static, Spring源码过滤掉了
    private String accessKey;
    private String secretKey;

    @Bean // 4
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
    }
}
