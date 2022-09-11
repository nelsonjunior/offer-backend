package br.com.offer.search.consumers

import br.com.offer.search.domain.dto.OfferDTO
import br.com.offer.search.services.OfferService
import io.awspring.cloud.messaging.config.annotation.NotificationMessage
import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy
import io.awspring.cloud.messaging.listener.annotation.SqsListener
import org.slf4j.LoggerFactory
import org.springframework.messaging.handler.annotation.Header
import org.springframework.stereotype.Component

@Component
class OfferConsumer (
    private val offerService: OfferService
        ) {

    private val logger = LoggerFactory.getLogger(OfferConsumer::class.java)

    @SqsListener("created-offer-queue", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    fun receiveMessage(
        @NotificationMessage offer: OfferDTO,
        @Header("SenderId") senderId: String?
    ) {
        logger.info("Received message from queue: $offer, SenderId $senderId")
        offerService.createdOfferProcess(offer)
    }
}