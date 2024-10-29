package com.dz.ims.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")   /*Configures CORS for all endpoints (/** matches all paths)*/
                .allowedOrigins("http://localhost","http://localhost:4200") /*Specifies the origins allowed to access the server*/
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")  /*Allows all headers.*/
                .allowCredentials(true); /* Indicates whether to allow cookies and HTTP authentication schemes.*/
    }
}
