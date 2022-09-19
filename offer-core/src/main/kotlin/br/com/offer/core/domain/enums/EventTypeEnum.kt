package br.com.offer.core.domain.enums

enum class EventTypeEnum(private val description: String) {

    CREATED_OFFER("created_offer"),

    CONFIRMED_OFFER("confirmed_offer");

    fun attributes() = mapOf("event" to description)
}