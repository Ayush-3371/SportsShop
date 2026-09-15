package com.example.exceptions;

import com.example.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptsHandler {
    @ExceptionHandler({UserNotFoundException.class, CustomerNotFoundException.class, AddressNotFoundException.class})
    public ResponseEntity<ErrorResponseDto> handleException(Exception e) {
        ErrorResponseDto error = new ErrorResponseDto(
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),
                e.getMessage()
        );
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }



}
