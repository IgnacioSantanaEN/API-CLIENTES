package com.clientes.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clientes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Integer idCliente;
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Column(name = "nombre_completo")
    private String nombreCompleto;

    private String rut;

    private String direccion;

    private String telefono;
}
