package br.com.offer.search.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "cloud.aws")
data class AWSProperties (
    val region: Region,
    val credentials: Credentials,
    val sqs: SQS
){
    data class Region(val static: String)

    data class Credentials(val accessKey: String, val secretKey: String)

    data class SQS(val endpoint: String)

}