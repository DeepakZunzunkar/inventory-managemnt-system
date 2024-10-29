package com.dz.ims.configuration;

import com.dz.ims.InventoryManagementSystemApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getInfo())
                .select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo getInfo() {
        String title = "Inventory Management System :  Api Documentation 1.0 ";
        String description = "build just for practice purpose ";
        String version = "1.0";
        String termAndService = "terms and service";
        String licenseOfApis = "License of Apis";
        return new ApiInfo(title, description, version, termAndService, new Contact("Deepak", "mywebsite:http://dz.com", "dz@ims.com"), licenseOfApis, "api license url", Collections.emptyList());
    }

}
