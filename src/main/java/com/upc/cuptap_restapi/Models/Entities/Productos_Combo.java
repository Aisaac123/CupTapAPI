package com.upc.cuptap_restapi.Models.Entities;

import com.upc.cuptap_restapi.Models.DTO.DTOLazyLoad.ComboLazy;
import com.upc.cuptap_restapi.Models.Interfaces.DTO.HasLazy;
import com.upc.cuptap_restapi.Models.Interfaces.Entities.CreateEntity;
import com.upc.cuptap_restapi.Models.Interfaces.Entities.DeleteEnity;
import com.upc.cuptap_restapi.Models.Interfaces.Entities.UpdateEntity;
import com.upc.cuptap_restapi.Models.Utils.NoUpdate;
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
public class Productos_Combo implements CreateEntity, UpdateEntity, DeleteEnity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(length = 4, nullable = false)
    int cantidad;
    @Setter
    @NoUpdate
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "nombre_producto")
    Producto producto;
    @Setter
    @NoUpdate
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "nombre_combo")
    Combo combo;
    @NoUpdate
    @Column(nullable = false)
    LocalDateTime fechaRegistro = LocalDateTime.now();

    public Productos_Combo(int cantidad, Producto producto, Combo combo) {
        this.cantidad = cantidad;
        this.producto = producto;
        this.combo = combo;
    }

    @Override
    public UpdateEntity cloneEntity() {
        try {
            return (Productos_Combo) this.clone();
        } catch (Exception e) {
            return null;
        }
    }
}
