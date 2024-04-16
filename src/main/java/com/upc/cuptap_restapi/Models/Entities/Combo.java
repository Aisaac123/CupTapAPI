package com.upc.cuptap_restapi.Models.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.upc.cuptap_restapi.Models.Interfaces.Entities.CrudEntity;
import com.upc.cuptap_restapi.Models.Interfaces.Entities.UpdateEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "Combos")
@NoArgsConstructor
@AllArgsConstructor
@Getter

public class Combo implements CrudEntity {

    public Combo(String nombre) {
        this.nombre = nombre;
    }

    public Combo(String nombre, String descripcion, double precio, int stock, boolean venta_activa, Promocion promocion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.venta_activa = venta_activa;
        this.promocion = promocion;
    }

    @Id
    @Column(length = 50)
    @Setter
    String nombre;

    @Setter
    @Column(columnDefinition = "TEXT")
    String descripcion;

    @Setter
    @Column(nullable = false)
    double precio;

    @Setter
    @Column(nullable = false)
    int stock;

    @Setter
    @Column(nullable = false)
    boolean venta_activa = true;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "combo")
    List<Productos_Combo> productosCombos;

    @OneToMany(mappedBy = "combo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"producto"})
    List<DetallesPedido> detalles;


    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_promocion")
    @JsonIgnoreProperties({"productos", "combos"})
    Promocion promocion;

    @Column(nullable = false)
    LocalDateTime fechaRegistro = LocalDateTime.now();

    @Override
    public UpdateEntity cloneEntity() {
        return null;
    }
}
