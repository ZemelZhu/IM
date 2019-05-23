package com.im.zemel.services;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class FileUploadService {
    @Value("${accessKey}")
    private String accessKey;
    @Value("${secretKey}")
    private String secretKey;
    @Value("${bucketName}")
    private String bucketName;
    @Value("${host}")
    private String host;
    @Value("${resultParameter}")
    private String resultParameter;
    private Zone z = Zone.autoZone();
    private Configuration c;
    private UploadManager uploadManager;

    public FileUploadService() {
        this.c = new Configuration(this.z);
        this.uploadManager = new UploadManager(this.c);
    }

    public Map upload(byte[] file) throws QiniuException {
        Auth auth = Auth.create(this.accessKey, this.secretKey);
        String token = auth.uploadToken(this.bucketName);
        String key = this.getImageName();
        this.uploadManager.put(file, key, token);
        Map map = new HashMap();
        map.put(this.resultParameter, this.host + key);
        return map;
    }

    public String getImageName() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
