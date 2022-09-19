package br.com.offer.search.repositories

import br.com.offer.search.domain.document.OfferDocument
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import org.springframework.stereotype.Repository

@Repository
interface OfferRepository : ElasticsearchRepository<OfferDocument, String> {

    fun findByDescription(description: String, pageable: Pageable): Page<OfferDocument>

}