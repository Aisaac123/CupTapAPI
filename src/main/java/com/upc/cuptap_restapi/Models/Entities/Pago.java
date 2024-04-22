package com.upc.cuptap_restapi.Models.Entities;

import com.upc.cuptap_restapi.Models.DTO.DTOLazyLoad.PagoLazy;
import com.upc.cuptap_restapi.Models.Interfaces.DTO.LazyDTO;
import com.upc.cuptap_restapi.Models.Interfaces.Entities.CrudEntity;
import com.upc.cuptap_restapi.Models.Interfaces.Entities.UpdateEntity;
import com.upc.cuptap_restapi.Models.Utils.NoUpdate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Entity(name = "Pagos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Pago implements CrudEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(nullable = false)
    double valor;
    @NoUpdate
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    Usuario usuario;
    @NoUpdate
    @Column(nullable = false)
    LocalDateTime fechaRegistro = LocalDateTime.now();

    public Pago(double valor, Usuario usuario) {
        this.valor = valor;
        this.usuario = usuario;
    }

    @Override
    public UpdateEntity cloneEntity() {
        try {
            return (Pago) this.clone();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public PagoLazy toLazy() {
        return new PagoLazy(id, valor,
                new PagoLazy.Usuario(usuario.id, usuario.cedula, usuario.nombre, usuario.apellidos, usuario.telefono), fechaRegistro);
    }
}
