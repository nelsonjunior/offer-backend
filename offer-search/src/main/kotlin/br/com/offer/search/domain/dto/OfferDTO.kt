package br.com.offer.search.domain.dto

import java.util.*

data class OfferDTO(
    val offerID: String?,
    val store: StoreDTO?,
    val category: CategoryDTO?,
    val description: String?,
    val slug: String?,
    val price: Double?,
    val lastPrice: Double?,
    val tag: String?,
    val images: List<String>?,
    val metrics: MetricsDTO?,
)
