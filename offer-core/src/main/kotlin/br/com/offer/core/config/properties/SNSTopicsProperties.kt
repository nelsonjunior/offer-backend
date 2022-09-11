package br.com.offer.core.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "sns.topics")
data class SNSTopicsProperties(
    val eventsOffer: String
)
