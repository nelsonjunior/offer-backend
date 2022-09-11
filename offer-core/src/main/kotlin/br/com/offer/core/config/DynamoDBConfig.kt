package br.com.offer.core.config

import br.com.offer.core.config.properties.AWSProperties
import com.amazonaws.auth.AWSCredentialsProvider
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.regions.Regions
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary


@Configuration
class DynamoDBConfig (
    private val awsProperties: AWSProperties
) {

    @Bean
    @Primary
    fun dynamoDBMapperConfig(): DynamoDBMapperConfig = DynamoDBMapperConfig.DEFAULT
    
    fun amazonAWSCredentialsProvider():AWSCredentialsProvider
        = AWSStaticCredentialsProvider(BasicAWSCredentials(awsProperties.credentials.accessKey, awsProperties.credentials.secretKey))

    @Bean
    @Primary
    fun dynamoDBMapper(
        amazonDynamoDB: AmazonDynamoDB,
        config: DynamoDBMapperConfig
    ): DynamoDBMapper = DynamoDBMapper(amazonDynamoDB, config)

    @Bean
    @Primary
    fun amazonDynamoDB(): AmazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
        .withCredentials(amazonAWSCredentialsProvider())
        .withRegion(Regions.fromName(awsProperties.region.static)).build()
}