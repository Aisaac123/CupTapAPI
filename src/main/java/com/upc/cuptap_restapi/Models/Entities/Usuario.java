package com.upc.cuptap_restapi.Models.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.upc.cuptap_restapi.Models.DTO.DTOLazyLoad.UsuarioLazy;
import com.upc.cuptap_restapi.Models.Interfaces.Entities.CrudEntity;
import com.upc.cuptap_restapi.Models.Utils.NoUpdate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Entity(name = "Usuarios")
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements CrudEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Setter
    @Column(length = 10, unique = true)
    String cedula;

    @Setter
    @Column(length = 50, nullable = false)
    String nombre;

    @Setter
    @Column(length = 50, nullable = false)
    String apellidos;

    @Setter
    @Column(length = 13, unique = true)
    String telefono;

    @Setter
    @Column(length = 50, unique = true)
    String username;

    @Setter
    @JsonIgnore
    @Column(length = 50)
    String password;

    @Setter
    @NoUpdate
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Pedido> pedidos = new ArrayList<>();


    @NoUpdate
    @Column(nullable = false)
    LocalDateTime fechaRegistro = LocalDateTime.now();

    public Usuario(String cedula, String nombre, String apellidos, String telefono, String username, String password) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.username = username;
        this.password = password;
    }

    public Usuario(String cedula) {
        this.cedula = cedula;
    }

    public void addPedido(Pedido pedido) {
        pedido.setUsuario(this);
        pedidos.add(pedido);
    }

    @Override
    public Usuario clone() {
        try {
            return (Usuario) super.clone();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public UsuarioLazy toLazy() {
        return new UsuarioLazy(id, cedula, nombre, apellidos, telefono, username, fechaRegistro);
    }
}
