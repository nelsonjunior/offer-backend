package br.com.offer.core.repositories

import br.com.offer.core.domain.entities.Offer
import org.socialsignin.spring.data.dynamodb.repository.EnableScan
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository

@EnableScan
interface PagingOfferRepository : PagingAndSortingRepository<Offer, String> {

    fun findByDescription(description: String, pageable: Pageable): Page<Offer>
}