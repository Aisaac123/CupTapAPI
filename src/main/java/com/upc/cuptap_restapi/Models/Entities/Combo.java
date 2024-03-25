package com.upc.cuptap_restapi.Models.Entities;

import com.upc.cuptap_restapi.Models.Interfaces.CrudEntity;
import com.upc.cuptap_restapi.Models.Interfaces.UpdateEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "Combos")
@NoArgsConstructor
@AllArgsConstructor
public class Combo implements CrudEntity {

    @Id
    @Column(length = 50)
    String nombre;
    @Column(columnDefinition = "TEXT")
    String descripcion;

    @Column(nullable = false)
    double precio;
    @Column(nullable = false)
    int stock;
    @Column(nullable = false)
    boolean venta_activa = true;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "combo")
    List<Productos_Combo> productosCombos;

    @OneToMany(mappedBy = "combo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<DetallesPedido> detalles;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_promocion")
    Promocion promocion;


    @Override
    public UpdateEntity cloneEntity() {
        return null;
    }
}
