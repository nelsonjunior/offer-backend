package br.com.offer.core.resources

import br.com.offer.core.domain.dto.CreateOfferDTO
import br.com.offer.core.repositories.PagingOfferRepository
import br.com.offer.core.services.OfferService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Tag(name = "Offer endpoint")
@RestController
@RequestMapping("/offers")
@Validated
class OfferResource (
    private val pagingOfferRepository: PagingOfferRepository,
    private val offerService: OfferService) {

    @Operation(summary = "List offers")
    @GetMapping
    fun listOffers(
        @RequestParam("storeID", required = true) storeID: String,
        @RequestParam("searchTerm", defaultValue = "") term: String
    ) = offerService.listOffers(storeID, term)

    @Operation(summary = "Find offer with value by ID")
    @GetMapping("/{id}")
    fun getOfferByID(
        @PathVariable("id") offerID: String
    ) = offerService.getById(offerID)

    @Operation(summary = "Find offer with value by Slug")
    @GetMapping("/slug/{slug}")
    fun getOfferBySlug(
        @PathVariable("slug") slug: String
    ) = offerService.getBySlug(slug)

    @Operation(summary = "Create a new offer")
    @PostMapping
    fun createOffer(
        @Valid
        @RequestBody createOffer: CreateOfferDTO
    ) = offerService.createOffer(createOffer)

    @Operation(summary = "Delete an offer by ID")
    @DeleteMapping("/{id}")
    fun deleteOffer(
        @PathVariable("id") offerID: String
    ): ResponseEntity<Void> {
        offerService.deleteOffer(offerID)
        return ResponseEntity.noContent().build()
    }

}