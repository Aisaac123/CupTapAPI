package com.upc.cuptap_restapi.Models.Entities;

import com.upc.cuptap_restapi.Models.Interface.ReadEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity(name = "Estados")
@NoArgsConstructor
@AllArgsConstructor
@Getter

public class Estado implements ReadEntity {
    @Id @Column(length = 50, nullable = false)
    String Nombre;

    @Column(columnDefinition = "TEXT")
    String Descripcion;

}
