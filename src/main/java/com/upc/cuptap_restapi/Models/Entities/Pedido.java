package com.upc.cuptap_restapi.Models.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.upc.cuptap_restapi.Models.Interfaces.Entities.CrudEntity;
import com.upc.cuptap_restapi.Models.Interfaces.Entities.UpdateEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity(name = "Pedidos")
@NoArgsConstructor
@AllArgsConstructor

public class Pedido implements CrudEntity {
    public Pedido(Long id) {
        this.id = id;
    }

    public Pedido(Usuario usuario, List<DetallesPedido> detalles) {
        this.usuario = usuario;
        this.detalles = detalles;
    }

    public Pedido(Usuario usuario) {
        this.usuario = usuario;
    }

    // Propiedades

    @Id
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    @Setter
    LocalDateTime fechaRegistro = LocalDateTime.now();

    @Column(nullable = false)
    long turno;

    @Column(nullable = false)
    double total;



    // Relaciones 1 a n

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

    // Relaciones n a 1




    @JsonIgnore
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<DetallesPedido> detalles;

    // Metodos

    @Override
    public UpdateEntity cloneEntity() {
        try {
            return (Pedido) this.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
