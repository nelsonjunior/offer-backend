package br.com.offer.core

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ConfigurationPropertiesScan
@ComponentScan("br.com.offer")
class CoreApplication

fun main(args: Array<String>) {
    runApplication<CoreApplication>(*args)
}
