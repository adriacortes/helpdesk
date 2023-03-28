package com.adria.hepldesk.resources.exceptions;

import com.adria.hepldesk.services.ObjectnotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandlerResourse {
    @ExceptionHandler(ObjectnotFoundException.class)
    public ResponseEntity<StandarError>
    objectnotFoundException(ObjectnotFoundException ex, HttpServletRequest request)
    {
        StandarError error = new StandarError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
                "Object not found",ex.getMessage(),request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
