package com.electiva.empleados.dto;

public record MensajeDTO<T>(
        boolean error,
        T respuesta
) {
}

