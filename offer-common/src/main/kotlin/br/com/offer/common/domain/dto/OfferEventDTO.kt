package br.com.offer.common.domain.dto;

import br.com.offer.common.domain.enums.EventTypeEnum
import java.util.Date

data class OfferEventDTO (
    val type: EventTypeEnum,
    val payload: Any,
    val date: Date = Date()
)
