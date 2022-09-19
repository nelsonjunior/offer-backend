package br.com.offer.search.domain.document

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document

@Document(indexName = "offer", createIndex = false)
data class OfferDocument(

    @Id
    val offerID: String?,
    val store: StoreDocument?,
    val category: CategoryDocument?,
    val description: String?,
    val slug: String?,
    val price: Double?,
    val lastPrice: Double?,
    val images: List<String>?,
    val tag: String?,
//    var metrics: MetricsDocument? = null,
)