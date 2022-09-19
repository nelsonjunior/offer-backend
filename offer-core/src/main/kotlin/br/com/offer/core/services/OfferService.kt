package br.com.offer.core.services

import br.com.offer.core.domain.enums.EventTypeEnum
import br.com.offer.core.config.exceptions.OfferException
import br.com.offer.core.config.properties.SNSTopicsProperties
import br.com.offer.core.domain.dto.CreateOfferDTO
import br.com.offer.core.domain.dto.OfferDTO
import br.com.offer.core.domain.entities.Offer
import br.com.offer.core.domain.enums.OfferStatusEnum
import br.com.offer.core.domain.mapper.OfferMapper
import br.com.offer.core.repositories.OfferRepository
import io.awspring.cloud.messaging.core.NotificationMessagingTemplate
import org.springframework.stereotype.Service

@Service
class OfferService(
    private val repository: OfferRepository,
    private val mapper: OfferMapper,
    private val notificationOffer: NotificationMessagingTemplate,
    private val topicsConfig: SNSTopicsProperties
) {


    fun listOffers(storeID: String,
                   term: String): List<OfferDTO> {

        return repository.findByStoreIDAndTerm(storeID, term).let {
            it.map { offer -> mapper.toDTO(offer) }
        }
    }

    fun getById(id: String): OfferDTO {

        val offer = repository.getById(id) ?: throw OfferException("Oferta não encontrada!")

        return mapper.toDTO(offer)
    }

    fun getBySlug(slug: String): OfferDTO {

        val offer = repository.getBySlug(slug) ?: throw OfferException("Oferta não encontrada!")

        return mapper.toDTO(offer)
    }

    fun createOffer(
        createOffer: CreateOfferDTO): Offer {

        if(createOffer.price > createOffer.lastPrice) {
            throw OfferException("Preço atual não pode ser maior que o preço anterior informado!")
        }

        val offer = repository.save(mapper.toEntity(createOffer))

        notificationOffer.convertAndSend(topicsConfig.eventsOffer,
            offer, EventTypeEnum.CREATED_OFFER.attributes())

        return offer
    }

    fun deleteOffer(id: String) {

        val offer = repository.getById(id) ?: throw OfferException("Oferta não encontrada!")

        repository.delete(offer)
    }

    fun confirmOffer(offerID: String) {

        val offer = repository.getById(offerID) ?: throw OfferException("Não foi possível confirmar. Oferta não encontrada!")

        offer.status = OfferStatusEnum.CONFIRMED

        repository.update(offer)
    }

}