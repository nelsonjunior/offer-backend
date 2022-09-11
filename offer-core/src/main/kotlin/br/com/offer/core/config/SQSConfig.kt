package br.com.offer.core.config

import br.com.offer.core.config.properties.AWSProperties
import com.amazonaws.auth.AWSCredentialsProvider
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.sqs.AmazonSQSAsync
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder
import com.fasterxml.jackson.databind.ObjectMapper
import io.awspring.cloud.messaging.config.QueueMessageHandlerFactory
import io.awspring.cloud.messaging.core.QueueMessagingTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.messaging.converter.MappingJackson2MessageConverter
import org.springframework.messaging.converter.MessageConverter
import org.springframework.messaging.handler.annotation.support.PayloadMethodArgumentResolver


@Configuration
class SQSConfig(
    private val awsProperties: AWSProperties
) {

    fun amazonAWSCredentialsProvider(): AWSCredentialsProvider
            = AWSStaticCredentialsProvider(BasicAWSCredentials(awsProperties.credentials.accessKey, awsProperties.credentials.secretKey))

    @Bean
    @Primary
    fun amazonSQSAsync() = AmazonSQSAsyncClientBuilder.standard()
        .withEndpointConfiguration(AwsClientBuilder
            .EndpointConfiguration(awsProperties.sqs.endpoint, awsProperties.region.static))
        .withCredentials(amazonAWSCredentialsProvider())
        .build()

//    @Bean
//    fun queueMessagingTemplate(amazonSQSAsync: AmazonSQSAsync)
//        = QueueMessagingTemplate(amazonSQSAsync)
//
//    @Bean
//    fun queueMessageHandlerFactory(mapper: ObjectMapper,
//                                   amazonSQSAsync: AmazonSQSAsync): QueueMessageHandlerFactory? {
//        val queueHandlerFactory = QueueMessageHandlerFactory()
//        queueHandlerFactory.setAmazonSqs(amazonSQSAsync)
//        queueHandlerFactory.setArgumentResolvers(
//            listOf(
//                PayloadMethodArgumentResolver(jackson2MessageConverter(mapper))
//            )
//        )
//        return queueHandlerFactory
//    }
//
//    private fun jackson2MessageConverter(mapper: ObjectMapper): MessageConverter {
//
//        val converter = MappingJackson2MessageConverter()
//        converter.objectMapper = mapper
//        return converter
//    }
}