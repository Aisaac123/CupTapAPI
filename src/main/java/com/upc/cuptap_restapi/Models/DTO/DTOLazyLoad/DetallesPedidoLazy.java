package com.upc.cuptap_restapi.Models.DTO.DTOLazyLoad;

import com.upc.cuptap_restapi.Models.Entities.DetallesPedido;
import com.upc.cuptap_restapi.Models.Interfaces.DTO.LazyDTO;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * IDTO for {@link com.upc.cuptap_restapi.Models.Entities.DetallesPedido}
 */
public record DetallesPedidoLazy(long id, int cantidad, double subtotal, Pedido pedido, Combo combo, Producto producto,
                                 LocalDateTime fechaRegistro) implements Serializable, LazyDTO<DetallesPedido> {
    /**
     * IDTO for {@link com.upc.cuptap_restapi.Models.Entities.Pedido}
     */
    public record Pedido(Long id, double total) implements Serializable {
    }

    /**
     * IDTO for {@link com.upc.cuptap_restapi.Models.Entities.Combo}
     */
    public record Combo(String nombre, double precio, byte[] imagen) implements Serializable {
    }

    /**
     * IDTO for {@link com.upc.cuptap_restapi.Models.Entities.Producto}
     */
    public record Producto(String nombre, double precio, byte[] imagen) implements Serializable {
    }
}