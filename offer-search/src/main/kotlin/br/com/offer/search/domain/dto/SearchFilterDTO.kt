package br.com.offer.search.domain.dto

data class SearchFilterDTO(
    val term: String = "",
    val storeID: String = "",
    val categories: List<String> = emptyList(),
    val minPrice: Double? = null,
    val maxPrice: Double? = null,
    val rate: Int? = null
)
