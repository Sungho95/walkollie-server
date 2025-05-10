package com.richbasoft.ollie

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@EnableJpaAuditing
@EnableAspectJAutoProxy
@SpringBootApplication
class OllieApplication

fun main(args: Array<String>) {
    runApplication<OllieApplication>(*args)
}
