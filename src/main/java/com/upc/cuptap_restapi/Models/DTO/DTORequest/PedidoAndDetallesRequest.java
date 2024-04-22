package com.upc.cuptap_restapi.Models.DTO.DTORequest;

import com.upc.cuptap_restapi.Models.Entities.DetallesPedido;
import com.upc.cuptap_restapi.Models.Entities.Estado;
import com.upc.cuptap_restapi.Models.Entities.Pedido;
import com.upc.cuptap_restapi.Models.Entities.Usuario;
import com.upc.cuptap_restapi.Models.Interfaces.DTO.RequestDTO;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * DTO for {@link com.upc.cuptap_restapi.Models.Entities.Pedido}
 */


public record PedidoAndDetallesRequest(@NotNull String usuarioCedula,
                                       @NotNull Set<DetallesPedidoRequestNoId> detalles,
                                       @NotNull String estadoNombre)

        implements Serializable, RequestDTO<Pedido> {
    @Override
    public Pedido toEntity() {

        Set<DetallesPedido> detallesPedidos = detalles.stream().map(DetallesPedidoRequestNoId::toEntity).collect(Collectors.toSet());
        var estado = new Estado();
        estado.setNombre(estadoNombre);
        return new Pedido(new Usuario(usuarioCedula), estado, detallesPedidos);
    }
}