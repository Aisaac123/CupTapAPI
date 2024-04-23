package com.upc.cuptap_restapi.Models.DTO.DTOLazyLoad;

import com.upc.cuptap_restapi.Models.Entities.Pedido;
import com.upc.cuptap_restapi.Models.Interfaces.DTO.LazyDTO;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * IDTO for {@link com.upc.cuptap_restapi.Models.Entities.Pedido}
 */
public record PedidoLazy(Long id, LocalDateTime fechaRegistro, double total, Usuario usuario,
                         Estado estado) implements Serializable, LazyDTO<Pedido> {
    /**
     * IDTO for {@link com.upc.cuptap_restapi.Models.Entities.Usuario}
     */


    public record Usuario(UUID id, String cedula, String nombre, String apellidos,
                          String telefono) implements Serializable {
    }

    /**
     * IDTO for {@link com.upc.cuptap_restapi.Models.Entities.Estado}
     */
    public record Estado(String Nombre, String Descripcion) implements Serializable {
    }
}