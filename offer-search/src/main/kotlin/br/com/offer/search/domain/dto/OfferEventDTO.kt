package br.com.offer.search.domain.dto;

import br.com.offer.search.domain.enums.EventTypeEnum
import java.util.Date

data class OfferEventDTO (
    val type: EventTypeEnum,
    val payload: Any,
    val date: Date = Date()
)
