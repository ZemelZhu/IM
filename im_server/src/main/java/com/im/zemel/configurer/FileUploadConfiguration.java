package com.im.zemel.configurer;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

@Configuration
public class FileUploadConfiguration {
    public FileUploadConfiguration() {
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("256MB");
        factory.setMaxRequestSize("512MB");
        return factory.createMultipartConfig();
    }
}
