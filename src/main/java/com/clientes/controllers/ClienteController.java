package com.clientes.controllers;

import com.clientes.dto.ClienteDTO;
import com.clientes.models.Cliente;
import com.clientes.services.ClienteService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Integer id) {
        Cliente cliente = service.obtenerPorId(id);

        if(cliente == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new Mensaje("Cliente no encontrado"));
        }

        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Cliente cliente) {
        Cliente guardado = service.guardar(cliente);
        if(guardado == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new Mensaje("No se pudo crear el cliente"));
        }
        return ResponseEntity.ok(guardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Cliente cliente) {
        Cliente actualizado = service.actualizar(id, cliente);

        if(actualizado == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new Mensaje("Cliente no encontrado"));
        }
        
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        return service.eliminar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Mensaje{
        private String mensaje;
    }
}