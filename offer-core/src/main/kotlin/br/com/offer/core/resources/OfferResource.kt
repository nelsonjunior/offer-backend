package br.com.offer.core.resources

import br.com.offer.core.domain.dto.CreateOfferDTO
import br.com.offer.core.services.OfferService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Tag(name = "Offer endpoint")
@RestController
@RequestMapping("/offers")
@Validated
class OfferResource (
    private val offerService: OfferService) {

    @Operation(summary = "Find offer with value by ID")
    @GetMapping("/{id}")
    fun getOffer(
        @PathVariable("id") offerID: String
    ) = offerService.getById(offerID)

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