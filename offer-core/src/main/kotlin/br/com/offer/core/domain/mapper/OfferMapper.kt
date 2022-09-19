package br.com.offer.core.domain.mapper

import br.com.offer.core.domain.dto.*
import br.com.offer.core.domain.entities.Category
import br.com.offer.core.domain.entities.Offer
import br.com.offer.core.domain.entities.Metrics
import br.com.offer.core.domain.entities.Store
import br.com.offer.core.domain.enums.OfferStatusEnum
import br.com.offer.core.util.toSlug
import br.com.offer.core.util.normalize
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.stereotype.Component

@Component
class OfferMapper {

    fun toEntity(createOfferDTO: CreateOfferDTO): Offer {
        return Offer(
            store = Store(
                createOfferDTO.store.storeID,
                createOfferDTO.store.name
            ),
            category = Category(
                createOfferDTO.category.categoryID,
                createOfferDTO.category.name
            ),
            description = createOfferDTO.description,
            slug = "${createOfferDTO.description.toSlug()}-${RandomStringUtils.randomAlphanumeric(8)}",
            price = createOfferDTO.price,
            lastPrice = createOfferDTO.lastPrice,
            tag = createOfferDTO.tag,
            images = createOfferDTO.images,
            metrics = Metrics(),
            status = OfferStatusEnum.PENDING,
            additionalInformation = createOfferDTO.additionalInformation,
            datePublish = createOfferDTO.datePublish,
            dateExpire = createOfferDTO.dateExpire,
            searchTerm = createOfferDTO.description.normalize(),
            storeID = createOfferDTO.store.storeID
        )
    }

    fun toDTO(offer: Offer) = OfferDTO(
        offerID = offer.offerID!!,
        store = StoreDTO(
            storeID = offer.store!!.storeID!!,
            name = offer.store!!.name!!
        ),
        category = CategoryDTO(
            categoryID = offer.category!!.categoryID!!,
            name = offer.category!!.name!!
        ),
        createdAt = offer.createdAt,
        description = offer.description,
        slug = offer.slug,
        price = offer.price,
        lastPrice = offer.lastPrice,
        tag = offer.tag,
        images = offer.images,
        metrics = MetricsDTO(
            offer.metrics!!.views,
            offer.metrics!!.likes,
            offer.metrics!!.comments
        ),
        status = offer.status.name,
        additionalInformation = offer.additionalInformation,
        datePublish = offer.datePublish,
        dateExpire = offer.dateExpire
    )

}