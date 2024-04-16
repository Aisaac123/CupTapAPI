package com.upc.cuptap_restapi.Models.Entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.upc.cuptap_restapi.Models.Interfaces.Entities.CrudEntity;
import com.upc.cuptap_restapi.Models.Interfaces.Entities.UpdateEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "DetallesPedido")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DetallesPedido implements CrudEntity {


    public DetallesPedido(int cantidad, Pedido pedido, Combo combo, Producto producto) {
        this.cantidad = cantidad;
        this.pedido = pedido;
        this.combo = combo;
        this.producto = producto;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Setter
    @Column(length = 4, nullable = false)
    int cantidad;

    @Column(nullable = false)
    double subtotal;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pedido_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Pedido pedido;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "combo_nombre")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Combo combo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "producto_nombre")
    Producto producto;

    @Column(nullable = false)
    LocalDateTime fechaRegistro = LocalDateTime.now();
    @Override
    public UpdateEntity cloneEntity() {
        try {
            return (DetallesPedido) this.clone();
        } catch (Exception e) {
            return null;
        }
    }
}
