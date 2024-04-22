package com.upc.cuptap_restapi.Models.Entities;

import com.upc.cuptap_restapi.Models.Interfaces.Entities.Entity;
import com.upc.cuptap_restapi.Models.Utils.NoUpdate;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@jakarta.persistence.Entity(name = "Administradores")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Admin implements Entity {
    @Id
    String cedula;
    @NoUpdate
    @Column(length = 50, nullable = false)
    String nombre;
    @NoUpdate
    @Column(length = 50, nullable = false)
    String apellidos;
    @NoUpdate
    @Column(length = 13, unique = true, nullable = false)
    String telefono;
    @NoUpdate
    @Column(length = 50, unique = true, nullable = false)
    String username;
    @NoUpdate
    @Column(length = 50, nullable = false)
    String password;
    @NoUpdate
    @Column(nullable = false)
    LocalDateTime fechaRegistro = LocalDateTime.now();

    public Admin(String cedula, String username, String password) {
        this.cedula = cedula;
        this.username = username;
        this.password = password;
    }
}
