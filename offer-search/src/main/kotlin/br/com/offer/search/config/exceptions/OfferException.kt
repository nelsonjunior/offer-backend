package br.com.offer.search.config.exceptions

import java.lang.RuntimeException

class OfferException : RuntimeException {

    constructor(message: String) : super(message)
    constructor(message: String, throwable: Throwable) : super(message, throwable)
}