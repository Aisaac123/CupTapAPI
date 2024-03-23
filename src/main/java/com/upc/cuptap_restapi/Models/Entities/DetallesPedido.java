package com.upc.cuptap_restapi.Models.Entities;

import com.upc.cuptap_restapi.Models.Interface.CrudEntity;
import com.upc.cuptap_restapi.Models.Interface.UpdateEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(nullable = false)
    double subtotal;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pedido_id")
    Pedido pedido;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "combo_nombre")
    Combo combo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "producto_nombre")
    Producto producto;
    @Override
    public UpdateEntity cloneEntity() {
        //TODO completar metodo
        return null;
    }
}
