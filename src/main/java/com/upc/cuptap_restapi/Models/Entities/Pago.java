package com.upc.cuptap_restapi.Models.Entities;

import com.upc.cuptap_restapi.Models.Interface.CrudEntity;
import com.upc.cuptap_restapi.Models.Interface.UpdateEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "Pagos")
public class Pago implements CrudEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(nullable = false)
    LocalDateTime fecha_registro = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pedido_id", nullable = false)
    Pedido pedido;

    @Override
    public UpdateEntity cloneEntity() {
        return null;
    }
}
