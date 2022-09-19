package br.com.offer.core.domain.dto

import java.util.Date
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull


data class CreateOfferDTO(

    @field:NotNull(message = "O campo 'Loja' é obrigatório")
    val store: StoreDTO,

    @field:NotNull(message = "O campo 'Categoria' é obrigatório")
    val category: CategoryDTO,

    @field:NotBlank(message = "O campo 'Descrição' é obrigatório")
    val description: String,

    @field:DecimalMin(value = "0.1", message = "O campo 'preço' deve ser maior que R$ 0,10")
    val price: Double,

    @field:DecimalMin(value = "0.1", message = "O campo 'preço anterior' deve ser maior que R$ 0,10")
    val lastPrice: Double,

    @field:NotEmpty(message = "O campo 'Imagem' deve conter no mínimo 1 imagem")
    val images: List<String>?,

    val additionalInformation: String,

    val datePublish: Date?,

    val dateExpire: Date?,

    val tag: String?
)
