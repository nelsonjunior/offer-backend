package br.com.offer.search.resources

import br.com.offer.search.services.OfferService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Offer endpoint")
@RestController
@RequestMapping("/offers")
class SuggestionResource (
    private val offerService: OfferService) {

    @Operation(summary = "Get suggestions offer with value query")
    @GetMapping("/suggestions")
    fun getSuggestions(@RequestParam(value = "q", required = false) query: String)
        = offerService.getSuggestions(query)
}