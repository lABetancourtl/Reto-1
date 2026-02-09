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

    /**
     * Crea un nuevo empleado en la base de datos.
     *
     * - Verifica que no exista un empleado con el mismo CC.
     * - Si ya existe, lanza IllegalStateException con un mensaje descriptivo.
     * - Si es válido, convierte el DTO a entidad y lo guarda en el repositorio.
     *
     * @param crearEmpleadoDTO DTO con los datos del empleado a crear (nombre, cargo, CC)
     * @throws IllegalStateException si ya existe un empleado con el mismo CC
     */
    @Override
    public void crearEmpleado(CrearEmpleadoDTO crearEmpleadoDTO) throws Exception {
        if (empleadoRepository.findBycc(crearEmpleadoDTO.cc()).isPresent()) {
            throw new IllegalStateException("El empleado con cc " + crearEmpleadoDTO.cc() + " ya existe");
        }
        Empleado empleado = empleadoMapper.createEmpleadoDTO(crearEmpleadoDTO);
        empleadoRepository.save(empleado);
    }

    /**
     * Consulta un empleado por su número de cédula (CC).
     *
     * - Recibe el CC como parámetro.
     * - Busca el empleado en el repositorio.
     * - Si se encuentra, devuelve el objeto Empleado.
     * - Si no se encuentra, lanza una excepción con un mensaje descriptivo.
     *
     * @param cc Número de cédula del empleado a consultar
     * @return Empleado encontrado
     * @throws Exception si no existe un empleado con el CC proporcionado
     */
    @Override
    public Empleado consultarEmpleado(Long cc) throws Exception {
        return empleadoRepository.findBycc(String.valueOf(cc))
                .orElseThrow(() ->
                        new Exception("El empleado con id " + cc + " no existe")
                );
    }

}
