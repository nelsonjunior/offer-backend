package br.com.offer.core.domain.dto

import java.util.*

data class OfferDTO(
    val offerID: String,
    val store: StoreDTO,
    val category: CategoryDTO,
    val createdAt: Date?,
    val description: String?,
    val slug: String?,
    val price: Double,
    val lastPrice: Double,
    val tag: String?,
    val images: List<String>?,
    val status: String?,
    val metrics: MetricsDTO?,
    val additionalInformation: String?,
    val datePublish: Date?,
    val dateExpire: Date?
)
