package com.xcy.springbootswagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author xcy
 * @date 2018/12/05 14:44
 * @description Swagger2配置类
 * @since V1.0.0
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))             //这里采用包含注解的方式来确定要显示的接口
                .apis(RequestHandlerSelectors.basePackage("com.xcy.springbootswagger.controller"))    //这里采用包扫描的方式来确定要显示的接口
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger helloWorld Doc")
                .description("Swagger CRUD 展示文档")
                .license("")
                .licenseUrl("")
                .termsOfServiceUrl("")
                .contact(new Contact("晴天", "", "13717736@qq.com"))
                .version("1.0")
                .build();
    }

}
