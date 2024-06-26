package com.hrs.hotel.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> notFoundHandler(ResourceNotFoundException ex){
        Map mpp = new HashMap();
        mpp.put("message", ex.getMessage());
        mpp.put("success", false);
        mpp.put("status", HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mpp);
    }
}
