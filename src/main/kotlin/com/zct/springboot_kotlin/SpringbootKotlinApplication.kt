package com.zct.springboot_kotlin

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
@MapperScan("com.zct.springboot_kotlin.app.mapper")
class SpringbootKotlinApplication

fun main(args: Array<String>) {
    SpringApplication.run(SpringbootKotlinApplication::class.java, *args)
}
