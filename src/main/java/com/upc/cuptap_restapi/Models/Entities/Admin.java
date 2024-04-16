package com.upc.cuptap_restapi.Models.Entities;

import com.upc.cuptap_restapi.Models.Interfaces.Entities.ReadEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "Administradores")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Admin implements ReadEntity {
    @Id
    String cedula;

    @Column(length = 50, nullable = false)
    String nombre;

    @Column(length = 50, nullable = false)
    String apellidos;

    @Column(length = 13, unique = true, nullable = false)
    String telefono;

    @Column(length = 50, unique = true, nullable = false)
    String username;

    @Column(length = 50, nullable = false)
    String password;

    @Column(nullable = false)
    LocalDateTime fechaRegistro = LocalDateTime.now();

    public Admin(String cedula, String username, String password) {
        this.cedula = cedula;
        this.username = username;
        this.password = password;
    }
}
