package com.sachet.notesapp.error_handler

import com.sachet.notesapp.errors.ApiError
import com.sachet.notesapp.errors.NoteNotFoundException
import org.springframework.context.support.DefaultMessageSourceResolvable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.support.WebExchangeBindException
import java.util.stream.Collectors

@ControllerAdvice
class GlobalErrorHandler {

    @ExceptionHandler(WebExchangeBindException::class)
    fun handleWebExchangeBindException(ex: WebExchangeBindException): ResponseEntity<ApiError>{
        val error = ex.bindingResult.allErrors.stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .collect(Collectors.joining(" | "))

        val apiError = ApiError(error, 400)

        return ResponseEntity(apiError, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(NoteNotFoundException::class)
    fun handleNoteNotFoundException(
        exception: NoteNotFoundException
    ): ResponseEntity<ApiError> = ResponseEntity(ApiError(exception.message, 404), HttpStatus.NOT_FOUND)

}