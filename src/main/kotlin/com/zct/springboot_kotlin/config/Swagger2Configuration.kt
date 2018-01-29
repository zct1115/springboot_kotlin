package com.zct.springboot_kotlin.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.swagger2.annotations.EnableSwagger2
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.service.ApiInfo
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket




@Configuration
@EnableSwagger2
class Swagger2Configuration {
    @Bean
    fun buildDocket(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInf())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zct.springboot_kotlin.app.controller"))//要扫描的API(Controller)基础包
                .paths(PathSelectors.any())
                .build()
    }

    private fun buildApiInf(): ApiInfo {
        return ApiInfoBuilder()
                .title("API接口文档说明")
                .contact("zct")
                .version("1.0")
                .build()
    }
}