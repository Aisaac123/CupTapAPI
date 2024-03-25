package com.upc.cuptap_restapi.Models.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.upc.cuptap_restapi.Models.Interface.CrudEntity;
import com.upc.cuptap_restapi.Models.Interface.UpdateEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "Usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Usuario implements CrudEntity {

    @Id
    String cedula;

    @Column(length = 50, nullable = false)
    String nombre;

    @Column(length = 50, nullable = false)
    String apellidos;

    @Column(length = 13, unique = true)
    String telefono;

    @Column(length = 50, unique = true)
    String username;

    @Column(length = 50)
    String password;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonIgnore
    List<Pedido> pedidos;

    @Override
    public UpdateEntity cloneEntity() {
        try {
            return (Usuario) this.clone();
        } catch (Exception e) {
            return null;
        }
    }

}
