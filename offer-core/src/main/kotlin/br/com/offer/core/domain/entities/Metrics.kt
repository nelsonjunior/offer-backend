package br.com.offer.core.domain.entities

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument

@DynamoDBDocument
data class Metrics (

    @DynamoDBAttribute
    var views: Long = 0,

    @DynamoDBAttribute
    var likes: Long = 0,

    @DynamoDBAttribute
    var comments: Long = 0,
)