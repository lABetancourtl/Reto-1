package com.electiva.empleados.controller;

import com.electiva.empleados.dto.CrearEmpleadoDTO;
import com.electiva.empleados.dto.MensajeDTO;
import com.electiva.empleados.model.Empleado;
import com.electiva.empleados.repository.EmpleadoRepository;
import com.electiva.empleados.service.EmpleadoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;
    private final EmpleadoRepository repository;

    /**
     * Endpoint para crear un nuevo empleado.
     *
     * - Recibe un JSON con los datos del empleado (nombre, cargo, CC) en un CrearEmpleadoDTO.
     * - Válida automáticamente que los campos no sean nulos o vacíos gracias a @Valid.
     * - Llama al service para crear el empleado.
     * - Devuelve un MensajeDTO con información del empleado creado si la operación es exitosa.
     *
     * @param empleado DTO con los datos del empleado a crear
     * @return ResponseEntity con un MensajeDTO que contiene los datos del empleado creado
     * @throws IllegalStateException si ya existe un empleado con el mismo CC
     */
    @PostMapping
    public ResponseEntity<MensajeDTO<String>> crearEmpleado(@Valid @RequestBody CrearEmpleadoDTO empleado) throws Exception {
        empleadoService.crearEmpleado(empleado);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Empleado Creado" + "\n" +
                "Nombre: " + empleado.nombre() + "\n" +
                "Cc: " + empleado.cc() + "\n" +
                "Cargo: " + empleado.cargo()));
    }


    /**
     * Endpoint para consultar un empleado por su número de cédula (CC).
     *
     * - Recibe el CC como variable de ruta.
     * - Llama al service para obtener el empleado correspondiente.
     * - Si se encuentra el empleado, devuelve un ResponseEntity con el objeto Empleado y estado 200 OK.
     * - Si no se encuentra el empleado, devuelve un ResponseEntity con el mensaje de error y estado 404 NOT FOUND.
     *
     * @param cc Número de cédula del empleado a consultar
     * @return ResponseEntity con el empleado encontrado o un mensaje de error si no existe
     */
    @GetMapping("/{cc}")
    public ResponseEntity<?> obtenerEmpleado(@PathVariable Long cc) {
        try {
            Empleado empleado = empleadoService.consultarEmpleado(cc);
            return ResponseEntity.ok(empleado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    // Consultar todos
    @GetMapping()
    public List<Empleado> listarEmpleados() {
        return repository.findAll();
    }

}