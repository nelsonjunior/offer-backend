package br.com.offer.core.domain.dto

data class ExceptionResponseDTO(
    val code: String,
    val message: String?,
    val details: Set<String?>? = null
)