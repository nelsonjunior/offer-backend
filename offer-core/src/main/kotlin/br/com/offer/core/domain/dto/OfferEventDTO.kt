package br.com.offer.core.domain.dto;

import br.com.offer.core.domain.enums.EventTypeEnum
import java.util.Date

data class OfferEventDTO (
    val type: EventTypeEnum,
    val payload: Any,
    val date: Date = Date()
)
