package com.tobimayr.hoover.exception;

import com.tobimayr.hoover.dto.HooverResultErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<HooverResultErrorDto> resourceNotFoundException(IllegalArgumentException ex, WebRequest request) {
        HooverResultErrorDto message = new HooverResultErrorDto(ex.getMessage());

        return new ResponseEntity<HooverResultErrorDto>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HooverResultErrorDto> methodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        HooverResultErrorDto message = new HooverResultErrorDto(ex.getFieldError().getDefaultMessage());

        return new ResponseEntity<HooverResultErrorDto>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HooverResultErrorDto> globalExceptionHandler(Exception ex, WebRequest request) {
        HooverResultErrorDto message = new HooverResultErrorDto(ex.getMessage());

        return new ResponseEntity<HooverResultErrorDto>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
