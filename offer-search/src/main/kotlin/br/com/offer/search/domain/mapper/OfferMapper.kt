package br.com.offer.search.domain.mapper

import br.com.offer.search.domain.document.CategoryDocument
import br.com.offer.search.domain.document.MetricsDocument
import br.com.offer.search.domain.document.OfferDocument
import br.com.offer.search.domain.document.StoreDocument
import br.com.offer.search.domain.dto.CategoryDTO
import br.com.offer.search.domain.dto.MetricsDTO
import br.com.offer.search.domain.dto.OfferDTO
import br.com.offer.search.domain.dto.StoreDTO
import org.springframework.context.annotation.Configuration

@Configuration
class OfferMapper {

    fun toDocument(offer: OfferDTO) = OfferDocument(
        offerID = offer.offerID,
        store = StoreDocument(
            offer.store!!.storeID,
            offer.store.name,
        ),
        category = CategoryDocument(
            offer.category!!.categoryID,
            offer.category.name,
        ),
        description =offer.description,
        slug = offer.slug,
        price = offer.price,
        lastPrice = offer.lastPrice,
        images = offer.images,
        tag = offer.tag,
//        metrics = MetricsDocument(
//            offer.metrics!!.views,
//            offer.metrics!!.likes,
//            offer.metrics!!.comments
//        )
    )

    fun toDTO(offer: OfferDocument) = OfferDTO(
        offerID = offer.offerID,
        store = StoreDTO(
            offer.store!!.storeID,
            offer.store.name,
        ),
        category = CategoryDTO(
            offer.category!!.categoryId,
            offer.category.name,
        ),
        description =offer.description,
        slug = offer.slug,
        price = offer.price,
        lastPrice = offer.lastPrice,
        images = offer.images,
        tag = offer.tag,
        metrics = MetricsDTO()
    )
}