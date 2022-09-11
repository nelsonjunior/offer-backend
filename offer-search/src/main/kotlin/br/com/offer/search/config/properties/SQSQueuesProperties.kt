package br.com.offer.search.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "sqs.queues")
data class SQSQueuesProperties(
    val confirmedCreatedOffer: String
)
