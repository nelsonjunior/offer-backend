package br.com.offer.core.consumers

import br.com.offer.core.domain.dto.OfferDTO
import br.com.offer.core.services.OfferService
import io.awspring.cloud.messaging.config.annotation.NotificationMessage
import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy
import io.awspring.cloud.messaging.listener.annotation.SqsListener
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class ConfirmedOfferConsumer (
    private val offerService: OfferService) {

    private val logger = LoggerFactory.getLogger(ConfirmedOfferConsumer::class.java)

    @SqsListener("confirmed-created-offer-queue", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    fun receiveMessage(
        @NotificationMessage offer: OfferDTO
    ) {

        logger.info("Received message from queue: $offer")

        offerService.confirmOffer(offer.offerID)
    }
}