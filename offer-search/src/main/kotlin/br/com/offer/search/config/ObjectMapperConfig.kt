package br.com.offer.search.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

//@Configuration
class ObjectMapperConfig {

//    @Bean
    fun objectMapper() = ObjectMapper()
}