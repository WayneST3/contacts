package dev.wayne.contacts.exception;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Hidden
@ControllerAdvice
public class ExceptionMapper {

    @ExceptionHandler({ApiException.class})
    public ResponseEntity<Object> handleApiException(ApiException ex) {
        return new ResponseEntity<>(ex.toString(), HttpStatus.valueOf(ex.getStatusCode()));
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        StringBuilder msg = new StringBuilder();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            msg.append(error.getDefaultMessage()).append("; ");
        }
        return new ResponseEntity<>(new ApiException(HttpServletResponse.SC_BAD_REQUEST, msg.toString()).toString(), HttpStatusCode.valueOf(HttpServletResponse.SC_BAD_REQUEST));
    }
}
