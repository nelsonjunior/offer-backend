package br.com.offer.search

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ConfigurationPropertiesScan
@ComponentScan("br.com.offer")
class OfferSearchApplication

fun main(args: Array<String>) {
    runApplication<OfferSearchApplication>(*args)
}
