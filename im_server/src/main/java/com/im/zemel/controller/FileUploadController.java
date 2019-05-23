package com.im.zemel.controller;

import com.im.zemel.services.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/FileUpload")
public class FileUploadController {
    @Autowired
    private FileUploadService fileUploadService;
    @Value("${resultParameter}")
    private String resultParameter;

    public FileUploadController() {
    }
    @RequestMapping({"/upload"})
    public Map upload(@RequestParam("myFile") MultipartFile file) {
        Map map = new HashMap();
        if (!file.isEmpty()) {
            try {
                return this.fileUploadService.upload(file.getBytes());
            } catch (IOException var4) {
                var4.printStackTrace();
                map.put(this.resultParameter, var4);
                return map;
            }
        } else {
            map.put(this.resultParameter, "文件不能为空");
            return map;
        }
    }
}
