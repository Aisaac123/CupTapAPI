package com.upc.cuptap_restapi.Models.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.upc.cuptap_restapi.Models.DTO.DTOLazyLoad.DetallesPedidoLazy;
import com.upc.cuptap_restapi.Models.Interfaces.Entities.CrudEntity;
import com.upc.cuptap_restapi.Models.Interfaces.Entities.UpdateEntity;
import com.upc.cuptap_restapi.Models.Utils.NoUpdate;
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


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Setter
    @Column(length = 4, nullable = false)
    int cantidad;

    @NoUpdate
    @Setter
    @Column(nullable = false)
    double subtotal;

    @NoUpdate
    @Setter
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "pedido_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnore
    Pedido pedido;

    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "combo_nombre", nullable = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Combo combo;

    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "producto_nombre", nullable = true)
    Producto producto;

    @NoUpdate
    @Column(nullable = false)
    LocalDateTime fechaRegistro = LocalDateTime.now();

    public DetallesPedido(int cantidad, Pedido pedido, Combo combo, Producto producto) {
        this.cantidad = cantidad;
        this.pedido = pedido;
        this.combo = combo;
        this.producto = producto;
    }

    @Override
    public UpdateEntity cloneEntity() {
        try {
            return (DetallesPedido) this.clone();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public DetallesPedidoLazy toLazy() {
        return new DetallesPedidoLazy(id, cantidad, subtotal,
                new DetallesPedidoLazy.Pedido(pedido.id, pedido.total),
                new DetallesPedidoLazy.Combo(combo.nombre, combo.precio, combo.imagen),
                new DetallesPedidoLazy.Producto(producto.nombre, producto.precio, producto.imagen), fechaRegistro);
    }
}
