package com.upc.cuptap_restapi.Models.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.upc.cuptap_restapi.Models.Interfaces.Entities.CrudEntity;
import com.upc.cuptap_restapi.Models.Interfaces.Entities.UpdateEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "Promociones")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Promocion implements CrudEntity {


    public Promocion(String nombre, String descripcion, LocalDateTime fecha_inicio, LocalDateTime fecha_fin, int descuento) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.descuento = descuento;
    }

    public Promocion(Long id) {
        this.id = id;
    }

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "promocion", fetch = FetchType.LAZY)
    @JsonIgnore
    List<Producto> productos;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "promocion", fetch = FetchType.LAZY)
    List<Combo> combos;


    @Column(nullable = false)
    LocalDateTime fechaRegistro = LocalDateTime.now();
    @Override
    public UpdateEntity cloneEntity() {
        try {
            return (Promocion) this.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
