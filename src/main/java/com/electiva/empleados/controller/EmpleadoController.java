package com.electiva.empleados.controller;

import com.electiva.empleados.dto.CrearEmpleadoDTO;
import com.electiva.empleados.dto.MensajeDTO;
import com.electiva.empleados.model.Empleado;
import com.electiva.empleados.repository.EmpleadoRepository;
import com.electiva.empleados.service.EmpleadoService;
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




    // Registrar empleado
    @PostMapping
    public ResponseEntity<MensajeDTO<String>> crearEmpleado(@RequestBody CrearEmpleadoDTO empleado) throws Exception {
        empleadoService.crearEmpleado(empleado);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Empleado Creado" + "\n" +
                "Nombre: " + empleado.nombre() + "\n" +
                "Cc: " + empleado.cc() + "\n" +
                "Cargo: " + empleado.cargo()));
    }


    // Consultar por cc
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