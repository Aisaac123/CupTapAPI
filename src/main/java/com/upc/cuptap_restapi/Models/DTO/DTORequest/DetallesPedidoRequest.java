package com.upc.cuptap_restapi.Models.DTO.DTORequest;

import com.upc.cuptap_restapi.Models.Entities.Combo;
import com.upc.cuptap_restapi.Models.Entities.DetallesPedido;
import com.upc.cuptap_restapi.Models.Entities.Pedido;
import com.upc.cuptap_restapi.Models.Entities.Producto;
import com.upc.cuptap_restapi.Models.Interfaces.DTO.RequestDTO;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

/**
 * DTO for {@link DetallesPedido}
 */


public record DetallesPedidoRequest(@NotNull @Positive int cantidad, @NotNull Long pedidoId,
                                    String comboNombre, String productoNombre)

        implements Serializable, RequestDTO<DetallesPedido> {
    public static boolean Validate(DetallesPedidoRequest detallesPedido) {
        return !(detallesPedido.comboNombre != null && detallesPedido.productoNombre != null);
    }

    @Override
    public DetallesPedido toEntity() {
        return new DetallesPedido(cantidad, new Pedido(pedidoId), new Combo(comboNombre), new Producto(productoNombre));
    }
}

