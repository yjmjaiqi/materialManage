package com.example.yjm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    // @Value("${upload.path}")
    // private String uploadPath;

    // @Autowired
    // private RequestInterceptor requestInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String uploadPath = System.getProperty("user.dir");
        uploadPath += "\\src\\main\\resources\\static\\images\\";
        /**
         * 访问路径：http://localhost:8000/images/cj_142207194912440320.png
         * "/avatar/**" 为前端URL访问路径
         * "file:" + uploadPath 是本地磁盘映射
         */
        registry.addResourceHandler("/images/**").addResourceLocations("file:" + uploadPath);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedMethods("GET","PUT","POST","DELETE")
                // .exposedHeaders("token")  // 允许前端访问 token 头部
                .maxAge(3600); // 1小时内不需要再预检（发OPTIONS请求）
    }

    // @Override
    // public void addInterceptors(InterceptorRegistry registry) {
    //     registry.addInterceptor(new RequestInterceptor())
    //             .addPathPatterns("/**")//"/material/login",
    //             .excludePathPatterns("/images/**", "/material/login", "/material/register", "/material/findPass", "/material/sendEmail");
    // }

}
