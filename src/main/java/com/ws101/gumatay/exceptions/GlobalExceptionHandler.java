package com.ws101.gumatay.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception ex) {
        
        Map<String, Object> errorDetails = new HashMap<>();
        
        errorDetails.put("timestamp", LocalDateTime.now().toString());
        errorDetails.put("message", ex.getMessage());
        
        // Determine status code
        HttpStatus status;
        if (ex.getMessage() != null && ex.getMessage().contains("not found")) {
            status = HttpStatus.NOT_FOUND; // 404
            errorDetails.put("error", "Not Found");
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR; // 500
            errorDetails.put("error", "Internal Server Error");
        }
        
        errorDetails.put("status", status.value());
        
        return new ResponseEntity<>(errorDetails, status);
    }
}

