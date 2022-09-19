package br.com.offer.core.config.exceptions.handler

import br.com.offer.core.config.exceptions.OfferException
import br.com.offer.core.domain.dto.ExceptionResponseDTO
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.stream.Collectors

@RestControllerAdvice
class RestExceptionHandler : ResponseEntityExceptionHandler() {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val errors= ex.bindingResult.fieldErrors.stream()
            .map { f -> "${f.field} : ${f.defaultMessage}" }
            .collect(Collectors.toSet())

        return ResponseEntity(
            ExceptionResponseDTO(
                HttpStatus.BAD_REQUEST.name,
                HttpStatus.BAD_REQUEST.reasonPhrase,
                errors), HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleException(e: Exception): ResponseEntity<Any?>? {
        return ResponseEntity(
            ExceptionResponseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.name,
                e.message), HttpStatus.INTERNAL_SERVER_ERROR
        )
    }

    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleEntityNotFoundException(ex: IllegalArgumentException): ResponseEntity<Any?>? {
        return ResponseEntity(
            ExceptionResponseDTO(
                HttpStatus.BAD_REQUEST.name,
                HttpStatus.BAD_REQUEST.reasonPhrase,
                setOf(ex.message)
            ), HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(OfferException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleEntityNotFoundException(ex: OfferException): ResponseEntity<Any?>? {
        return ResponseEntity(
            ExceptionResponseDTO(
                HttpStatus.BAD_REQUEST.name,
                HttpStatus.BAD_REQUEST.reasonPhrase,
                setOf(ex.message)
            ), HttpStatus.BAD_REQUEST
        )
    }

}