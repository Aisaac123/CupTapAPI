package com.upc.cuptap_restapi.Models.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.upc.cuptap_restapi.Models.DTO.DTOLazyLoad.ProductoLazy;
import com.upc.cuptap_restapi.Models.Interfaces.Entities.CrudEntity;
import com.upc.cuptap_restapi.Models.Utils.NoUpdate;
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
    @Setter
    @NoUpdate
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto")
    List<Productos_Combo> productosCombos;
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_promocion", nullable = true)
    @JsonIgnoreProperties({"productos", "combos"})
    Promocion promocion = null;
    @NoUpdate
    @Column(nullable = false)
    LocalDateTime fechaRegistro = LocalDateTime.now();

    @Setter
    @Lob
    byte[] imagen;

    public Producto(String nombre, String descripcion, double precio, int stock, boolean venta_activa, Promocion promocion, byte[] imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.venta_activa = venta_activa;
        this.promocion = promocion;
        this.imagen = imagen;
    }

    public Producto(String nombre) {
        this.nombre = nombre;
    }

    public Producto(String nombre, String descripcion, double precio, int stock, boolean venta_activa, byte[] imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.venta_activa = venta_activa;
        this.imagen = imagen;
    }


    @Override
    public Producto clone() {
        try {
            return (Producto) super.clone();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ProductoLazy toLazy() {
        var p = promocion;
        if (p != null)
            return new ProductoLazy(nombre, descripcion, precio, stock, venta_activa,
                    new ProductoLazy.Promocion(p.id, p.nombre, p.descripcion, p.fecha_inicio, p.fecha_fin, p.descuento),
                    imagen, fechaRegistro);
        return new ProductoLazy(nombre, descripcion, precio, stock, venta_activa, null, imagen, fechaRegistro);
    }
}
