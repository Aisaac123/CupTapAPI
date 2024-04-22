package com.upc.cuptap_restapi.Models.DTO.DTORequest;

import com.upc.cuptap_restapi.Models.Entities.DetallesPedido;
import com.upc.cuptap_restapi.Models.Entities.Estado;
import com.upc.cuptap_restapi.Models.Entities.Pedido;
import com.upc.cuptap_restapi.Models.Entities.Usuario;
import com.upc.cuptap_restapi.Models.Interfaces.DTO.RequestDTO;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.Validate;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * DTO for {@link Pedido}
 */


public record PedidoAndDetallesRequestNoId(@NotNull Set<DetallesPedidoRequestNoId> detalles,
                                           String estadoNombre)

        implements Serializable, RequestDTO<Pedido> {
    @Override
    public Pedido toEntity() {

        Set<DetallesPedido> detallesPedidos = detalles.stream().map(DetallesPedidoRequestNoId::toEntity).collect(Collectors.toSet());
        var estado = new Estado();
        estado.setNombre(estadoNombre);
        return new Pedido(new Usuario(),estado, detallesPedidos);
    }

}