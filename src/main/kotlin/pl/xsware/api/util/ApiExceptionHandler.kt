package pl.xsware.api.util

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import pl.xsware.domain.model.dto.ErrorResponse
import java.time.LocalDateTime

@ControllerAdvice
class ApiExceptionHandler {

    @ExceptionHandler(Throwable::class)
    fun handleUserNotFound(ex: Throwable): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            timestamp = LocalDateTime.now(),
            status = HttpStatus.BAD_REQUEST.value(),
            error = "Not Found",
            message = ex.message ?: "Wystąpił niespodziewany błąd!"
        )
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

}