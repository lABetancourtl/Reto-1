package com.electiva.empleados.repository;

import com.electiva.empleados.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    Optional<Empleado> findBycc(String cc);
}
