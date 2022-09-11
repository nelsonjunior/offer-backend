package br.com.offer.core.domain.mapper

import br.com.offer.core.domain.dto.CreateOfferDTO
import br.com.offer.core.domain.dto.OfferDTO
import br.com.offer.core.domain.dto.OfferMetricsDTO
import br.com.offer.core.domain.entities.Offer
import br.com.offer.core.domain.entities.OfferMetrics
import br.com.offer.core.domain.enums.OfferStatusEnum
import br.com.offer.core.util.toSlug
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.stereotype.Component

@Component
class OfferMapper {

    fun toEntity(createOfferDTO: CreateOfferDTO): Offer {
        return Offer(
            storeID = createOfferDTO.storeID,
            description = createOfferDTO.description,
            price = createOfferDTO.price,
            lastPrice = createOfferDTO.lastPrice,
            tag = createOfferDTO.tag,
            image = createOfferDTO.image,
            slug = "${createOfferDTO.description.toSlug()}-${RandomStringUtils.randomAlphanumeric(8)}",
            metrics = OfferMetrics(),
            status = OfferStatusEnum.PENDING
        )
    }

    fun toDTO(offer: Offer) = OfferDTO(
        offer.offerID!!,
        offer.storeID!!,
        offer.createdAt,
        offer.description,
        offer.slug,
        offer.price,
        offer.lastPrice,
        offer.tag,
        offer.image,
        offer.status.name,
        OfferMetricsDTO(
            offer.metrics!!.views,
            offer.metrics!!.likes,
            offer.metrics!!.comments
        )
    )

}