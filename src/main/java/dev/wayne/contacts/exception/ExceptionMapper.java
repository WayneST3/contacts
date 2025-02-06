package dev.wayne.contacts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionMapper {

    @ExceptionHandler({ApiException.class})
    public ResponseEntity<Object> handleAll(ApiException ex, WebRequest request) {
        return new ResponseEntity<>(ex.toString(), HttpStatus.valueOf(ex.getStatusCode()));
    }
}
