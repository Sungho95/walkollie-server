package com.richbasoft.ollie.common.config.aop

import com.richbasoft.ollie.common.utils.logger
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.Logger
import org.springframework.stereotype.Component

@Aspect
@Component
class LoggingAspect {
    private val log: Logger = logger()

    @Pointcut("execution(* com.richbasoft.ollie.interfaces.*.*..*(..))")
    fun controllerExecute() {

    }

    @Around("controllerExecute()")
    fun logControllerExecution(joinPoint: ProceedingJoinPoint): Any {
        val className = joinPoint.signature.declaringTypeName
        val methodName = joinPoint.signature.name
        log.info("### 요청 발생!! --- $className.$methodName")
        try {
            val result = joinPoint.proceed()
            log.info("### 응답 성공!! --- $className.$methodName")
            return result
        } catch (e: Throwable) {
            log.error("### 예외 발생!! --- $className.$methodName", e)
            throw e
        }
    }
}