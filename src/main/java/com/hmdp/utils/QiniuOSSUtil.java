package com.hmdp.utils;


import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@Slf4j
public class QiniuOSSUtil {
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;

    /**
     * 文件上传
     *
     * @param bytes
     * @param objectName
     * @return
     */
    public String upload(byte[] bytes, String objectName) {

        Auth auth = Auth.create(accessKeyId,accessKeySecret);
        Configuration cfg = new Configuration(Region.region0());
        UploadManager uploadManager = new UploadManager(cfg);
        String Token = auth.uploadToken(bucketName);
        try {
            Response response = uploadManager.put(bytes,objectName,Token);
        } catch (QiniuException e) {
            System.out.println("Error Message:" + e.getMessage());
        }


        //文件访问路径规则 https://BucketName.Endpoint/ObjectName
        StringBuilder stringBuilder = new StringBuilder("http://");
        stringBuilder
                .append(endpoint)
                .append("/")
                .append(objectName);
        log.info("文件上传到:{}", stringBuilder.toString());

        return stringBuilder.toString();
    }
}
