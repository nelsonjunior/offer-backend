package br.com.offer.core.config

import br.com.offer.core.config.properties.AWSProperties
import com.amazonaws.auth.AWSCredentialsProvider
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.sns.AmazonSNS
import com.amazonaws.services.sns.AmazonSNSAsyncClientBuilder
import io.awspring.cloud.messaging.core.NotificationMessagingTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary


@Configuration
class SNSConfig(
    private val awsProperties: AWSProperties
) {
    fun amazonAWSCredentialsProvider(): AWSCredentialsProvider
            = AWSStaticCredentialsProvider(BasicAWSCredentials(awsProperties.credentials.accessKey, awsProperties.credentials.secretKey))

    @Bean
    @Primary
    fun snsAyncClient() = AmazonSNSAsyncClientBuilder.standard()
        .withCredentials(amazonAWSCredentialsProvider())
        .withRegion(awsProperties.region.static)
        .build()

    @Bean
    fun notificationMessagingTemplate(amazonSNS: AmazonSNS) = NotificationMessagingTemplate(amazonSNS)
}