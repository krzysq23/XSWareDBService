package pl.xsware.api.util

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component
import pl.xsware.api.AuthController
import java.util.concurrent.TimeUnit

@Aspect
@Component
class LoggingAspect {

    val log: Logger = LogManager.getLogger(AuthController::class.java)

    @Around("within(@org.springframework.web.bind.annotation.RestController *)")
    fun logAround(joinPoint: ProceedingJoinPoint): Any? {
        val methodName = joinPoint.signature.name
        val start = System.nanoTime()

        log.info("▶ START: $methodName")
        return try {
            val result = joinPoint.proceed()
            result
        } finally {
            val elapsed = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start)
            log.info("⏹ STOP: $methodName (took ${elapsed} ms)")
        }
    }
}