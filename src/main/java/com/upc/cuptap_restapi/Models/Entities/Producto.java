package com.upc.cuptap_restapi.Models.Entities;

import com.upc.cuptap_restapi.Models.Interface.CrudEntity;
import com.upc.cuptap_restapi.Models.Interface.UpdateEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.util.Lazy;

import java.util.List;

@Entity(name = "Productos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Producto implements CrudEntity {

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto")
    List<Productos_Combo> productosCombos;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<DetallesPedido> detalles;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_promocion")
    Promocion promocion;
    @Override
    public UpdateEntity cloneEntity() {
        return null;
    }

}
