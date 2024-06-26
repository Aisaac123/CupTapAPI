package com.upc.cuptap_restapi.Models.DTO.DTOLazyLoad;

import com.upc.cuptap_restapi.Models.Entities.Pago;
import com.upc.cuptap_restapi.Models.Interfaces.DTO.LazyDTO;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * IDTO for {@link com.upc.cuptap_restapi.Models.Entities.Pago}
 */
public record PagoLazy(long id, double valor, Pedido pedido,
                       LocalDateTime fechaRegistro) implements Serializable, LazyDTO<Pago> {
    /**
     * IDTO for {@link com.upc.cuptap_restapi.Models.Entities.Pedido}
     */
    public record Pedido(Long id, LocalDateTime fechaRegistro, double total, Usuario usuario) implements Serializable {
        /**
         * IDTO for {@link com.upc.cuptap_restapi.Models.Entities.Usuario}
         */
        public record Usuario(UUID id, String cedula, String nombre, String apellidos, String telefono,
                              String username) implements Serializable {
        }
    }
}