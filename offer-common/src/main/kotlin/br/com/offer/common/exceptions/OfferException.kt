package br.com.offer.common.exceptions

import java.lang.RuntimeException

class OfferException : RuntimeException {

    constructor(message: String) : super(message)
    constructor(message: String, throwable: Throwable) : super(message, throwable)
}