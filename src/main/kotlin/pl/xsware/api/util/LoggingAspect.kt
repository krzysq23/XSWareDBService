package pl.xsware.api.util

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component
import pl.xsware.api.AuthController

@Aspect
@Component
class LoggingAspect {

    val log: Logger = LogManager.getLogger(AuthController::class.java)

    @Around("within(@org.springframework.web.bind.annotation.RestController *)")
    fun logAround(joinPoint: ProceedingJoinPoint): Any? {
        val methodName = joinPoint.signature.name
        log.info("START method: $methodName")
        val result = joinPoint.proceed()
        log.info("STOP method: $methodName, result: $result")
        return result
    }
}