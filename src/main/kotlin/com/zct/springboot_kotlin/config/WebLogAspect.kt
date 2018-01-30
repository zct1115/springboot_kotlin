package com.zct.springboot_kotlin.config

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes


@Aspect
@Component
class WebLogAspect {

    private val logger=LoggerFactory.getLogger(javaClass)

    @Pointcut("execution(public * com.zct.springboot_kotlin.app.controller..*.*(..))")
    fun webLog(){}

    @Before("webLog()")
    @Throws(Throwable::class)
    fun dobefore(joinPoint:JoinPoint){
        // 接收到请求，记录请求内容
        val attributes = RequestContextHolder.getRequestAttributes() as ServletRequestAttributes
        val request = attributes.request
        // 记录下请求内容
        logger.info("URL : " + request.requestURL.toString())
        logger.info("HTTP_METHOD : " + request.method)
        logger.info("IP : " + request.remoteAddr)
        val enu = request.parameterNames
        while (enu.hasMoreElements()) {
            val name = enu.nextElement() as String
            logger.info("name:{},value:{}", name, request.getParameter(name))
        }
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    @Throws(Throwable::class)
    fun doAfterReturning(ret: Any?) {
        // 处理完请求，返回内容
        logger.info("RESPONSE : " + ret)
    }
}