package com.electiva.empleados.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CrearEmpleadoDTO(
        @NotBlank @Length        ( max = 100)         String nombre,
        @NotBlank @Length        ( max = 100)         String cargo,
        @NotBlank @Length        ( max = 100)         String cc
) {
}
