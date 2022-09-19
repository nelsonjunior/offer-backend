package br.com.offer.search.resources

import br.com.offer.search.domain.dto.PageFilterDTO
import br.com.offer.search.domain.dto.SearchFilterDTO
import br.com.offer.search.services.OfferService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Search endpoint")
@RestController
@RequestMapping("/offers")
class SearchResource (
    private val offerService: OfferService
) {

    @Operation(summary = "Search offer with value query")
    @GetMapping("/search")
    fun search(
        filter: SearchFilterDTO,
        page: PageFilterDTO
    ) = offerService.search(filter, page)
}