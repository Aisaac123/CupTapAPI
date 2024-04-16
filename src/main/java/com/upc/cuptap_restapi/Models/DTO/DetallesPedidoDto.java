package com.upc.cuptap_restapi.Models.DTO;

import com.upc.cuptap_restapi.Models.Entities.Combo;
import com.upc.cuptap_restapi.Models.Entities.DetallesPedido;
import com.upc.cuptap_restapi.Models.Entities.Pedido;
import com.upc.cuptap_restapi.Models.Entities.Producto;
import com.upc.cuptap_restapi.Models.Interfaces.DTO.IDTO;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO for {@link DetallesPedido}
 */


public record DetallesPedidoDto(@NotNull @Positive int cantidad, @NotNull Long pedidoId,
                                String comboNombre, String productoNombre)

                                implements Serializable, IDTO<DetallesPedido> {
    @Override
    public DetallesPedido toEntity() {
        return new DetallesPedido(cantidad, new Pedido(pedidoId), new Combo(comboNombre), new Producto(productoNombre));
    }
}