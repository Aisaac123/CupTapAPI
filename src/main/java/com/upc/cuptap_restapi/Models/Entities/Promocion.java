package com.upc.cuptap_restapi.Models.Entities;

import com.upc.cuptap_restapi.Models.Interface.CrudEntity;
import com.upc.cuptap_restapi.Models.Interface.UpdateEntity;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

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
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "promocion")
    List<Producto> productos;

    @Setter
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "promocion")
    List<Combo> combos;

    @Override
    public UpdateEntity cloneEntity() {
        try {
            return (Promocion) this.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
