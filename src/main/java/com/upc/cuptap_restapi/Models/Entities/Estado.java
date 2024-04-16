package com.upc.cuptap_restapi.Models.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.upc.cuptap_restapi.Models.Interfaces.Entities.ReadEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity(name = "Estados")
@NoArgsConstructor
@AllArgsConstructor
@Getter

public class Estado implements ReadEntity {
    @Id
    @Column(length = 50, nullable = false)
    @JsonProperty("nombre")

    String Nombre;
    @JsonProperty("descripcion")
    @Column(columnDefinition = "TEXT")
    String Descripcion;

    @Setter
    @Column(nullable = false)
    LocalDateTime fechaRegistro = LocalDateTime.now();
}
