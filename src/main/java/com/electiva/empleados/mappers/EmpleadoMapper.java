package com.electiva.empleados.mappers;


import com.electiva.empleados.dto.CrearEmpleadoDTO;
import com.electiva.empleados.model.Empleado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmpleadoMapper {

    @Mapping(target = "id", ignore = true)
    Empleado createEmpleadoDTO(CrearEmpleadoDTO crearEmpleadoDTO);
}
