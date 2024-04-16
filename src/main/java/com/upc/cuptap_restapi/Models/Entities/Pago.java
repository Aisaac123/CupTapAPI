package com.upc.cuptap_restapi.Models.Entities;

import com.upc.cuptap_restapi.Models.Interfaces.Entities.CrudEntity;
import com.upc.cuptap_restapi.Models.Interfaces.Entities.UpdateEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "Pagos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Pago implements CrudEntity {


    public Pago(double valor, Usuario usuario) {
        this.valor = valor;
        this.usuario = usuario;
    }

    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Setter
    @Column(nullable = false)
    double valor;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    Usuario usuario;

    @Setter
    @Column(nullable = false)
    LocalDateTime fechaRegistro = LocalDateTime.now();
    @Override
    public UpdateEntity cloneEntity() {
        try {
            return (Pago) this.clone();
        } catch (Exception e) {
            return null;
        }
    }
}
