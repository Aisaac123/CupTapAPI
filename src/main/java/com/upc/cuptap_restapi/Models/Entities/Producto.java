package com.upc.cuptap_restapi.Models.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

@Entity(name = "Productos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Producto implements CrudEntity {

    public Producto(String nombre, String descripcion, double precio, int stock, boolean venta_activa, Promocion promocion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.venta_activa = venta_activa;
        this.promocion = promocion;
    }

    public Producto(String nombre) {
        this.nombre = nombre;
    }

    @Setter
    @Id
    @Column(length = 50)
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto")
    @JsonIgnore
    List<Productos_Combo> productosCombos;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_promocion")
    @JsonIgnoreProperties({"productos", "combos"})
    Promocion promocion;

    @Column(nullable = false)
    LocalDateTime fechaRegistro = LocalDateTime.now();
    @Override
    public UpdateEntity cloneEntity() {
        try {
            return (Producto) this.clone();
        } catch (Exception e) {
            return null;
        }    }

}
