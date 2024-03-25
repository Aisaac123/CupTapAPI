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

@Entity(name = "Pedidos")
@NoArgsConstructor
@AllArgsConstructor
@Getter

public class Pedido implements CrudEntity {

    // Propiedades

    @Id
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(nullable = false)
    @Setter
    LocalDateTime FechaRegistro = LocalDateTime.now();

    @Column(nullable = false)
    long turno;

    @Column(nullable = false)
    @Setter
    double total;

    // Relaciones 1 a n

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_cedula", nullable = false)
    Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "estado_nombre", nullable = false)
    Estado estado;

    // Relaciones n a 1

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Pago> pagos;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<DetallesPedido> detalles;

    // Metodos

    @Override
    public UpdateEntity cloneEntity() {
        try {
            return (Pedido) this.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
