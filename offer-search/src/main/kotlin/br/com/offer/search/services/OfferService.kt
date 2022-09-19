package br.com.offer.search.services

import br.com.offer.search.domain.enums.EventTypeEnum
import br.com.offer.search.config.properties.SNSTopicsProperties
import br.com.offer.search.domain.document.OfferDocument
import br.com.offer.search.domain.dto.OfferDTO
import br.com.offer.search.domain.dto.OfferSuggestionDTO
import br.com.offer.search.domain.dto.PageFilterDTO
import br.com.offer.search.domain.dto.SearchFilterDTO
import br.com.offer.search.domain.mapper.OfferMapper
import br.com.offer.search.repositories.OfferRepository
import io.awspring.cloud.messaging.core.NotificationMessagingTemplate
import org.elasticsearch.common.unit.Fuzziness
import org.elasticsearch.index.query.QueryBuilder
import org.elasticsearch.index.query.QueryBuilders
import org.springframework.data.domain.PageRequest
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder
import org.springframework.data.elasticsearch.core.query.Query
import org.springframework.stereotype.Service

private const val INDEX_OFFER = "offer"

@Service
class OfferService (
    private val mapper: OfferMapper,
    private val topicsProperties: SNSTopicsProperties,
    private val notificationOffer: NotificationMessagingTemplate,
    private val offerRepository: OfferRepository,
    private val elasticsearchOperations: ElasticsearchOperations) {

    fun createdOfferProcess(offer: OfferDTO) {

        notificationOffer.convertAndSend(topicsProperties.eventsOffer,
            offer, EventTypeEnum.CONFIRMED_OFFER.attributes())

        val offerDocument = mapper.toDocument(offer)

        offerRepository.save(offerDocument)
    }

    fun getSuggestions(query: String): List<OfferSuggestionDTO> {

        val queryBuilder: QueryBuilder = QueryBuilders
            .matchQuery("description","$query")
            .fuzziness(Fuzziness.AUTO)

        val searchQuery: Query = NativeSearchQueryBuilder()
            .withQuery(queryBuilder)
            .withMaxResults(20)
            .build()

        val offersHits =
            elasticsearchOperations
                .search(searchQuery, OfferDocument::class.java, IndexCoordinates.of(INDEX_OFFER))

        val suggestions = mutableListOf<OfferSuggestionDTO>()

        offersHits.searchHits.forEach {
            suggestions.add(
                OfferSuggestionDTO(it.content.offerID, it.content.description, it.content.slug)
            )
        }

        return suggestions
    }

    fun search(filter: SearchFilterDTO,
               page: PageFilterDTO
    ): List<OfferDTO> {

        val filterBuilder = QueryBuilders.boolQuery()

        if (filter.term.isNotBlank()) {
            filterBuilder
                .must(QueryBuilders
                    .matchQuery("description", filter.term)
                    .fuzziness(Fuzziness.AUTO)
            )
        }

        if (filter.storeID.isNotBlank() ) {
            filterBuilder.filter(
                QueryBuilders.termQuery("storeID", filter.storeID)
            )
        }

        if (filter.categories.isNotEmpty()) {
            filterBuilder.filter(
                QueryBuilders.termsQuery("category.categoryId", filter.categories)
            )
        }

        if (filter.minPrice != null && filter.maxPrice != null) {
            filterBuilder.filter(
                QueryBuilders.rangeQuery("price")
                    .gte(filter.minPrice)
                    .lte(filter.maxPrice)
            )
        }

        val searchQuery = NativeSearchQueryBuilder()
            .withFilter(filterBuilder)
            .withPageable(PageRequest.of(page.page, page.limit))

        if (filter.term.isNotBlank()) {
            searchQuery
                .withQuery(QueryBuilders
                    .matchQuery("description", filter.term)
                    .fuzziness(Fuzziness.AUTO)
                )
        }

        val offersHits =
            elasticsearchOperations
                .search(searchQuery.build(), OfferDocument::class.java, IndexCoordinates.of(INDEX_OFFER))

        val offers = mutableListOf<OfferDTO>()

        offersHits.searchHits.forEach {
            offers.add(mapper.toDTO(it.content))
        }
        return offers
    }
}