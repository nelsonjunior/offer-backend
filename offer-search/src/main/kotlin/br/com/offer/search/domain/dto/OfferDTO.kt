package br.com.offer.search.domain.dto

import java.util.*

data class OfferDTO(
    val offerID: String?,
    val storeID: String?,
    val createdAt: Date?,
    val description: String?,
    val slug: String?,
    val price: Double?,
    val lastPrice: Double?,
    val tag: String?,
    val image: String?,
)
