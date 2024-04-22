package com.upc.cuptap_restapi.Models.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.upc.cuptap_restapi.Models.DTO.DTOLazyLoad.PedidoLazy;
import com.upc.cuptap_restapi.Models.Interfaces.Entities.CrudEntity;
import com.upc.cuptap_restapi.Models.Interfaces.Entities.UpdateEntity;
import com.upc.cuptap_restapi.Models.Utils.NoUpdate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Entity(name = "Pedidos")
@NoArgsConstructor
@AllArgsConstructor

public class Pedido implements CrudEntity {
    @Id
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NoUpdate
    @Column(nullable = false)
    @Setter
    LocalDateTime fechaRegistro = LocalDateTime.now();
    @Column(nullable = false)
    double total;

    // Propiedades
    @NoUpdate
    @Setter
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "usuario_cedula", nullable = false)
    @JsonIgnoreProperties(value = {"pedidos", "username", "password"})
    Usuario usuario;

    @Setter
    @JsonProperty("estado")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "estado_nombre", nullable = false)
    @JsonIgnoreProperties(value = {"descripcion"})
    Estado estado;

    @NoUpdate
    @Setter
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Set<DetallesPedido> detalles = new HashSet<>();


    // Relaciones 1 a n

    public Pedido(Long id) {
        this.id = id;
    }

    public Pedido(Usuario usuario, Set<DetallesPedido> detalles) {
        this.usuario = usuario;
        this.detalles = detalles;
    }

    // Relaciones n a 1


    public Pedido(Usuario usuario) {
        this.usuario = usuario;
    }

    public Pedido(Usuario usuario, Estado estado, Set<DetallesPedido> detalles) {
        this.usuario = usuario;
        this.estado = estado;
        this.detalles = detalles;
    }

    public Pedido(Usuario usuario, Estado estado) {
        this.usuario = usuario;
        this.estado = estado;
    }
    // Metodos

    @Override
    public UpdateEntity cloneEntity() {
        try {
            return (Pedido) this.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public PedidoLazy toLazy() {
        return new PedidoLazy(id, fechaRegistro, total,
                new PedidoLazy.Usuario(usuario.id, usuario.cedula, usuario.nombre, usuario.apellidos, usuario.telefono),
                new PedidoLazy.Estado(estado.Nombre, estado.Descripcion));
    }

}
