package br.com.offer.core.domain.dto

import javax.validation.constraints.DecimalMin
import javax.validation.constraints.NotBlank


data class CreateOfferDTO(

    @field:NotBlank(message = "O campo 'Loja' é obrigatório")
    val storeID: String,

    @field:NotBlank(message = "O campo 'Descrição' é obrigatório")
    val description: String,

    @field:DecimalMin(value = "0.1", message = "O campo 'preço' deve ser maior que R$ 0,10")
    val price: Double,

    @field:DecimalMin(value = "0.1", message = "O campo 'preço anterior' deve ser maior que R$ 0,10")
    val lastPrice: Double,

    val tag: String?,

    val image: String?
)
