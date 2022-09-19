package br.com.offer.search.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "elasticsearch")
data class AWSElasticSearchProperties(
    val endpoint: String,
    val username: String,
    val password: String,
    val dev: Boolean
)
