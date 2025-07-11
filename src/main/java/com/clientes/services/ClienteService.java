package com.clientes.services;

import com.clientes.dto.ClienteDTO;
import com.clientes.models.Cliente;
import com.clientes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public Cliente guardar(Cliente cliente) {
        return repository.save(cliente);
    }

    public List<ClienteDTO> listar() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Cliente obtenerPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Cliente actualizar(Integer id, Cliente cliente) {
        Cliente existente = repository.findById(id).orElse(null);
        if (existente != null) {
            existente.setIdUsuario(cliente.getIdUsuario());
            existente.setNombreCompleto(cliente.getNombreCompleto());
            existente.setRut(cliente.getRut());
            existente.setDireccion(cliente.getDireccion());
            existente.setTelefono(cliente.getTelefono());
            return repository.save(existente);
        }
        return null;
    }

    public boolean eliminar(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    // Métodos auxiliares
    private ClienteDTO toDTO(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setIdCliente(cliente.getIdCliente());

        dto.setLink("http://localhost:8086/api/clientes/" + cliente.getIdCliente());
        return dto;
    }
}