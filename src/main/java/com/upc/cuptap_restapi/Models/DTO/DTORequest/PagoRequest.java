package com.upc.cuptap_restapi.Models.DTO.DTORequest;

import com.upc.cuptap_restapi.Models.Entities.Pago;
import com.upc.cuptap_restapi.Models.Interfaces.DTO.RequestDTO;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

/**
 * DTO for {@link com.upc.cuptap_restapi.Models.Entities.Pago}
 */
public record PagoRequest(@Positive double valor,
                          @NotNull PedidoDTOPagoRequest pedido) implements Serializable, RequestDTO<Pago> {
    @Override
    public Pago toEntity() {
        return new Pago(valor, new com.upc.cuptap_restapi.Models.Entities.Pedido(pedido.id));
    }

    /**
     * DTO for {@link com.upc.cuptap_restapi.Models.Entities.Pedido}
     */
    public record PedidoDTOPagoRequest(@NotNull @Positive Long id) implements Serializable {
    }
}