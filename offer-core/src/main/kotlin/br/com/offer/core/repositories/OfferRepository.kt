package br.com.offer.core.repositories

import br.com.offer.core.domain.entities.Offer
import br.com.offer.core.util.normalize
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression
import com.amazonaws.services.dynamodbv2.model.AttributeValue
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue
import org.springframework.stereotype.Repository


@Repository
class OfferRepository(
    private val dynamoDBMapper: DynamoDBMapper
) {

    fun findByStoreIDAndTerm(storeID: String, term: String) : List<Offer> {

        val scanExpression = DynamoDBScanExpression()
            .withFilterExpression("storeID = :storeID and contains(searchTerm, :term)")
            .withExpressionAttributeValues(
                mapOf(
                    ":storeID" to AttributeValue().withS(storeID),
                    ":term" to AttributeValue().withS(term.normalize())
                )
            )

        scanExpression.let {

            return dynamoDBMapper.scan(Offer::class.java, it).toList()
        }
    }

    fun getById(offerID: String): Offer? {
        return dynamoDBMapper.load(Offer::class.java, offerID)
    }

    fun getBySlug(slug: String): Offer? {
        DynamoDBScanExpression().withFilterExpression("slug = :slug")
            .withExpressionAttributeValues(
                mapOf(":slug" to AttributeValue().withS(slug))
            ).let {
                return dynamoDBMapper.scan(Offer::class.java, it).firstOrNull()
        }
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