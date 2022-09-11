package br.com.offer.core.repositories

import br.com.offer.core.domain.entities.Offer
import br.com.offer.core.domain.enums.OfferStatusEnum
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression
import com.amazonaws.services.dynamodbv2.model.AttributeValue
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue
import org.springframework.stereotype.Repository


@Repository
class OfferRepository(
    private val dynamoDBMapper: DynamoDBMapper
) {

    fun getById(offerID: String): Offer? {
        return dynamoDBMapper.load(Offer::class.java, offerID)
    }

    fun save(offer: Offer): Offer {
        dynamoDBMapper.save(offer)
        return offer
    }

    fun delete(offer: Offer) {
        dynamoDBMapper.delete(offer)
    }

    fun update(offer: Offer) {
        dynamoDBMapper.save(offer,
            DynamoDBSaveExpression()
                .withExpectedEntry("offerID",
                    ExpectedAttributeValue(AttributeValue().withS(offer.offerID)))
        )

    }

}