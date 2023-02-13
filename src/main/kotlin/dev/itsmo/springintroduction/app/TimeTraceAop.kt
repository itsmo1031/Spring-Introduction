package dev.itsmo.springintroduction.app

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component
import kotlin.jvm.Throws

@Aspect
@Component
class TimeTraceAop {
    @Around("execution(* dev.itsmo.springintroduction..*(..))")
    @Throws(Throwable::class)
    fun execute(joinPoint: ProceedingJoinPoint): Any? {
        val start = System.currentTimeMillis()
        println("🌟 START: ${joinPoint.toString()}")

        try {
            return joinPoint.proceed()
        } finally {
            val duration = System.currentTimeMillis() - start

            println("⏰ END: ${joinPoint.toString()} ${duration}ms")
        }
    }
}