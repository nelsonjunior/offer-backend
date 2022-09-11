package br.com.offer.core.domain.dto

import java.util.*

data class OfferDTO(
    val offerID: String,
    val storeID: String,
    val createdAt: Date,
    val description: String?,
    val slug: String?,
    val price: Double,
    val lastPrice: Double,
    val tag: String?,
    val image: String?,
    val status: String?,
    val metrics: OfferMetricsDTO?,
)
