package com.electiva.empleados.service.impl;

import com.electiva.empleados.dto.CrearEmpleadoDTO;
import com.electiva.empleados.mappers.EmpleadoMapper;
import com.electiva.empleados.model.Empleado;
import com.electiva.empleados.repository.EmpleadoRepository;
import com.electiva.empleados.service.EmpleadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;
    private final EmpleadoMapper empleadoMapper;

    @Override
    public void crearEmpleado(CrearEmpleadoDTO crearEmpleadoDTO) throws Exception {
        if (empleadoRepository.findBycc(crearEmpleadoDTO.cc()).isPresent()) {
            throw new Exception("El empleado con cc: " + crearEmpleadoDTO.cc() + "ya existe");
        }

        Empleado empleado = empleadoMapper.createEmpleadoDTO(crearEmpleadoDTO);

        empleadoRepository.save(empleado);
    }

    @Override
    public Empleado consultarEmpleado(Long cc) throws Exception {
        return empleadoRepository.findBycc(String.valueOf(cc))
                .orElseThrow(() ->
                        new Exception("El empleado con id " + cc + " no existe")
                );
    }

}
