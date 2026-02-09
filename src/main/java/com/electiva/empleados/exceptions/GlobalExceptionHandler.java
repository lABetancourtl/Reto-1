package com.electiva.empleados.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja los errores 404 cuando no se encuentra un endpoint (NoHandlerFoundException).
     * Devuelve un JSON con los siguientes campos:
     * - timestamp: fecha y hora de la petición
     * - status: 404
     * - error: mensaje de error genérico ("Recurso no encontrado")
     * - path: URI que causó el error
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(NoHandlerFoundException ex) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", Instant.now());
        body.put("status", 404);
        body.put("error", "Recurso no encontrado");
        body.put("path", ex.getRequestURL());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(body);
    }

    /**
     * Maneja la excepción cuando se intenta crear un empleado con un CC que ya existe.
     * Devuelve un JSON con:
     * - status: 400
     * - error: "No se puede crear el empleado"
     * - message: mensaje de la excepción
     * - path: URI de la petición
     */
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalState(
            IllegalStateException ex,
            HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of(
                        "status", 400,
                        "error", "No se puede crear el empleado",
                        "message", ex.getMessage(),
                        "path", request.getRequestURI()
                )
        );
    }

}
