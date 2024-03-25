package com.upc.cuptap_restapi.Models.Entities;

import com.upc.cuptap_restapi.Models.Interface.CrudEntity;
import com.upc.cuptap_restapi.Models.Interface.UpdateEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity(name = "Productos_del_combo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Productos_Combo implements CrudEntity {

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

    @Override
    public UpdateEntity cloneEntity() {
        return null;
    }
}
