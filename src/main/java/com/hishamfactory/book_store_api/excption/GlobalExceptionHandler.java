package com.hishamfactory.book_store_api.excption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<?> handleBookNotFoundException(BookNotFoundException bookNotFoundException){
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), bookNotFoundException.getMessage(),"none");
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<?> handleCategoryNotFoundException(CategoryNotFoundException categoryNotFoundException){
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(),categoryNotFoundException.getMessage(),"none");
        return new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_FOUND);
    }
}
