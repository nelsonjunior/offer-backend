package br.com.offer.search.services

import br.com.offer.common.domain.dto.OfferEventDTO
import br.com.offer.common.domain.enums.EventTypeEnum
import br.com.offer.search.config.properties.SNSTopicsProperties
import br.com.offer.search.config.properties.SQSQueuesProperties
import br.com.offer.search.domain.dto.OfferDTO
import com.amazonaws.services.sns.AmazonSNSAsync
import com.amazonaws.services.sns.AmazonSNSClient
import com.amazonaws.services.sns.model.MessageAttributeValue
import com.amazonaws.services.sns.model.PublishRequest
import com.fasterxml.jackson.databind.ObjectMapper
import io.awspring.cloud.messaging.core.NotificationMessagingTemplate
import io.awspring.cloud.messaging.core.QueueMessagingTemplate
import org.springframework.stereotype.Service
import java.util.*

@Service
class OfferService (
    private val topicsProperties: SNSTopicsProperties,
    private val notificationOffer: NotificationMessagingTemplate) {

    fun createdOfferProcess(offer: OfferDTO) {

        notificationOffer.convertAndSend(topicsProperties.eventsOffer,
            offer, EventTypeEnum.CONFIRMED_OFFER.attributes())

    }
}