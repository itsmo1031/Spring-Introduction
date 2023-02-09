package dev.itsmo.springintroduction

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringIntroductionApplication

fun main(args: Array<String>) {
    runApplication<SpringIntroductionApplication>(*args)
}
