package com.upc.cuptap_restapi.Models.DTO.DTORequest;

import com.upc.cuptap_restapi.Models.Entities.Combo;
import com.upc.cuptap_restapi.Models.Entities.DetallesPedido;
import com.upc.cuptap_restapi.Models.Entities.Pedido;
import com.upc.cuptap_restapi.Models.Entities.Producto;
import com.upc.cuptap_restapi.Models.Interfaces.DTO.RequestDTO;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

/**
 * IDTO for {@link com.upc.cuptap_restapi.Models.Entities.DetallesPedido}
 */
public record DetallesPedidoRequest(
        @Positive int cantidad,
        String comboNombre,
        String productoNombre) implements Serializable, RequestDTO<DetallesPedido> {
    @Override
    public DetallesPedido toEntity() {
        return new DetallesPedido(cantidad, new Pedido(), new Combo(comboNombre), new Producto(productoNombre));
    }
}