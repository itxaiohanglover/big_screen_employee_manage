package com.tyut.employee.controller;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * @author xh
 * @Date 2022/5/28
 */
@RestController
public class OssController {
    @Autowired
    MinioClient minioClient;

    @Value("${oss.bucket}")
    String bucket;
    @Value("${oss.endpoint}")
    String endpoint;

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        // 上传
        String path = UUID.randomUUID().toString(); // 文件名，使用 UUID 随机
        try {
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucket) // 存储桶
                    .object(path) // 文件名
                    .stream(file.getInputStream(), file.getSize(), -1) // 文件内容
                    .contentType(file.getContentType()) // 文件类型
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 拼接路径
        return String.format("%s/%s/%s", endpoint, bucket, path);
    }
}
