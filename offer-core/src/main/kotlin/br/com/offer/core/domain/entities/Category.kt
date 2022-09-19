package br.com.offer.core.domain.entities

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument

@DynamoDBDocument
data class Category (

    @DynamoDBAttribute
    var categoryID: String? = null,

    @DynamoDBAttribute
    var name: String? = null
)
