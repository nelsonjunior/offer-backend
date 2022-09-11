package br.com.offer.search.config

import br.com.offer.search.config.properties.AWSProperties
import com.amazonaws.auth.AWSCredentialsProvider
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.sqs.AmazonSQSAsync
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder
import io.awspring.cloud.messaging.core.QueueMessagingTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary


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

    @Bean
    fun queueMessagingTemplate(amazonSQSAsync: AmazonSQSAsync)
        = QueueMessagingTemplate(amazonSQSAsync)

}