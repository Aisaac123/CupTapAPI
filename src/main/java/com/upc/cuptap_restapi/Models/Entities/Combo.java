package com.upc.cuptap_restapi.Models.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.upc.cuptap_restapi.Models.DTO.DTOLazyLoad.ComboLazy;
import com.upc.cuptap_restapi.Models.Interfaces.Entities.CrudEntity;
import com.upc.cuptap_restapi.Models.Utils.NoUpdate;
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
    @Setter
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "combo")
    List<Productos_Combo> productosCombos;
    @NoUpdate
    @Setter
    @OneToMany(mappedBy = "combo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //@JsonIgnoreProperties({"combo, producto"})
    @JsonIgnore
    List<DetallesPedido> detalles;
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_promocion")
    @JsonIgnoreProperties({"productos", "combos"})
    Promocion promocion;
    @NoUpdate
    @Column(nullable = false)
    LocalDateTime fechaRegistro = LocalDateTime.now();
    @Setter
    @Lob
    byte[] imagen;

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

    public Combo(String nombre, String descripcion, double precio, int stock, boolean venta_activa) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.venta_activa = venta_activa;
    }

    @Override
    public Combo clone() {
        try {
            return (Combo) super.clone();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ComboLazy toLazy() {
        var p = getPromocion();
        if (p != null)
            return new ComboLazy(nombre, descripcion, precio, stock, venta_activa,
                    new ComboLazy.Promocion(p.id, p.nombre, p.descripcion, p.fecha_inicio, p.fecha_fin, p.descuento), fechaRegistro, imagen);
        return new ComboLazy(nombre, descripcion, precio, stock, venta_activa, null, fechaRegistro, imagen);

    }
}
