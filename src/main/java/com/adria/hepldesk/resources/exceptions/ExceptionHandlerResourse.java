package com.adria.hepldesk.resources.exceptions;

import com.adria.hepldesk.services.exceptions.DataIntegrityViolationException;
import com.adria.hepldesk.services.exceptions.ObjectnotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerResourse {
    @ExceptionHandler(ObjectnotFoundException.class)
    public ResponseEntity<StandarError>
    objectnotFoundException(ObjectnotFoundException ex, HttpServletRequest request)
    {
        StandarError error = new StandarError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
                "Objeto não encontrado",ex.getMessage(),request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandarError>
    dataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request)
    {
        StandarError error = new StandarError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
                "Violação de dados",ex.getMessage(),request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }






}
