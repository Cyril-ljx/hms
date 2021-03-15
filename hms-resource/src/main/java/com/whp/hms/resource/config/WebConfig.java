package com.whp.hms.resource.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.util.UUID;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Value(value = "${system.imgUpLoadPath}")
    private String imgUpLoadPath;


    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedMethods("*")
                        .allowedOrigins("*")
                        .allowCredentials(true)
                        .allowedMethods("GET", "POST", "DELETE", "PUT")
                        .maxAge(3600);
            }
        };
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        isExist(imgUpLoadPath);//创建文件夹
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("file:" + imgUpLoadPath);
    }

    /**
     * 判断文件夹是否存在,不存在则创建文件夹
     */
    private void isExist(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /**
     * 生成新的文件名
     *
     * @param fileOriginName 源文件名
     * @return 新文件名
     */
    private static String getFileName(String fileOriginName) {
        if (fileOriginName == null) {
            return null;
        }
        String fileName = UUID.randomUUID().toString().replace("-", "");
        String suffix = fileOriginName.substring(fileOriginName.lastIndexOf("."));
        return fileName + suffix;
    }
}
