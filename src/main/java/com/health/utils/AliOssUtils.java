package com.health.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.health.config.AliOssProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.util.UUID;

@Component
public class AliOssUtils {

    @Autowired
    private AliOssProperties aliOssProperties;

    /**
     * 实现文件上传到阿里云OSS
     * @param multipartFile
     * @return 返回上传成功后文件的完整访问URL
     */
    public String upload(MultipartFile multipartFile) {
        // 从配置类中获取配置信息
        String endpoint = aliOssProperties.getEndpoint();
        String accessKeyId = aliOssProperties.getAccessKeyId();
        String accessKeySecret = aliOssProperties.getAccessKeySecret();
        String bucketName = aliOssProperties.getBucketName();

        OSS ossClient = null;
        try {
            // 获取上传文件的输入流
            InputStream inputStream = multipartFile.getInputStream();

            // 避免文件覆盖，生成唯一的文件名
            String originalFilename = multipartFile.getOriginalFilename();
            if (originalFilename == null) {
                throw new RuntimeException("上传文件名为空");
            }
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String objectName = UUID.randomUUID().toString() + suffix;

            // 创建OSSClient实例
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 执行上传操作
            ossClient.putObject(bucketName, objectName, inputStream);

            // 拼接并返回文件的公网访问地址
            return "https://" + bucketName + "." + endpoint + "/" + objectName;
        } catch (Exception e) {
            e.printStackTrace();
            return null; // 上传失败返回null
        } finally {
            // 关闭OSSClient
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}