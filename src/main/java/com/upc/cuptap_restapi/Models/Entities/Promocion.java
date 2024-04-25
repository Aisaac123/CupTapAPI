package com.upc.cuptap_restapi.Models.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.upc.cuptap_restapi.Models.DTO.DTOLazyLoad.PromocionLazy;
import com.upc.cuptap_restapi.Models.DTO.DTORequest.PromocionRequest;
import com.upc.cuptap_restapi.Models.Interfaces.Entities.CrudEntity;
import com.upc.cuptap_restapi.Models.Utils.NoUpdate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "Promociones")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Promocion implements CrudEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Setter
    @Column(length = 50, unique = true, nullable = false)
    String nombre;

    @Setter
    @Column(columnDefinition = "TEXT")
    String descripcion;

    @Setter
    @Column(nullable = false)
    LocalDateTime fecha_inicio;

    @Setter
    @Column(nullable = false)
    LocalDateTime fecha_fin;

    @Setter
    @Column(length = 3, nullable = false)
    int descuento;

    @Setter
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "promocion", fetch = FetchType.EAGER)
    Set<Producto> productos = new HashSet<>();

    @Setter
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "promocion", fetch = FetchType.EAGER)
    Set<Combo> combos = new HashSet<>();

    @NoUpdate
    @Column(nullable = false)
    LocalDateTime fechaRegistro = LocalDateTime.now();

    public Promocion(String nombre, String descripcion, LocalDateTime fecha_inicio, LocalDateTime fecha_fin, int descuento) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.descuento = descuento;
    }

    public Promocion(String nombre, String descripcion, LocalDateTime fecha_inicio, LocalDateTime fecha_fin, int descuento, Set<Producto> productos, Set<Combo> combos) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.descuento = descuento;
        this.productos = productos;
        this.combos = combos;
    }
    public void AddProducto(Producto producto){
        productos.add(producto);
    }

    public void AddProducto(Set<Producto> productos){
        this.productos.addAll(productos);
    }

    public void AddCombos(Combo combo){
        combos.add(combo);
    }

    public void AddCombos(Set<Combo> combos){
        this.combos.addAll(combos);
    }

    public Promocion(Long id) {
        this.id = id;
    }

    @Override
    public Promocion clone() {
        try {
            return (Promocion) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public PromocionLazy toLazy() {
        return new PromocionLazy(id, nombre, descripcion, fecha_inicio, fecha_fin, descuento, fechaRegistro);
    }


}
