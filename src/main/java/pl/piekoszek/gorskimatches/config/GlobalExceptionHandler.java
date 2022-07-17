package pl.piekoszek.gorskimatches.config;

import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(JwtException.class)
    protected ResponseEntity<Object> handleJwtException(
            RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
