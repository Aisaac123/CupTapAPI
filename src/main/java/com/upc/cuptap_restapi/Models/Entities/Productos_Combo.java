package com.upc.cuptap_restapi.Models.Entities;

import com.upc.cuptap_restapi.Models.Interfaces.Entities.CrudEntity;
import com.upc.cuptap_restapi.Models.Interfaces.Entities.UpdateEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity(name = "Productos_del_combo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Productos_Combo implements CrudEntity {


    public Productos_Combo(int cantidad, Producto producto, Combo combo) {
        this.cantidad = cantidad;
        this.producto = producto;
        this.combo = combo;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(length = 4, nullable = false)
    int cantidad;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "nombre_producto")
    Producto producto;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "nombre_combo")
    Combo combo;

    @Column(nullable = false)
    LocalDateTime fechaRegistro = LocalDateTime.now();
    @Override
    public UpdateEntity cloneEntity() {
        try {
            return (Productos_Combo) this.clone();
        } catch (Exception e) {
            return null;
        }    }
}
