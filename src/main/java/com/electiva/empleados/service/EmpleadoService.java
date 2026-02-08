package com.electiva.empleados.service;

import com.electiva.empleados.dto.CrearEmpleadoDTO;
import com.electiva.empleados.model.Empleado;

public interface EmpleadoService {

    void crearEmpleado(CrearEmpleadoDTO CrearEmpleadoDTO) throws Exception;

    Empleado consultarEmpleado(Long cc) throws Exception;
}
