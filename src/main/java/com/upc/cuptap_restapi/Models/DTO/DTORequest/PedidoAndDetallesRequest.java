package com.upc.cuptap_restapi.Models.DTO.DTORequest;

import com.upc.cuptap_restapi.Models.Entities.DetallesPedido;
import com.upc.cuptap_restapi.Models.Entities.Pedido;
import com.upc.cuptap_restapi.Models.Entities.Usuario;
import com.upc.cuptap_restapi.Models.Interfaces.DTO.RequestDTO;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * DTO for {@link com.upc.cuptap_restapi.Models.Entities.Pedido}
 */


public record PedidoAndDetallesRequest(String usuarioCedula,
                                       @NotNull List<DetallesPedidoRequest> detalles)

        implements Serializable, RequestDTO<Pedido> {
    @Override
    public Pedido toEntity() {

        Set<DetallesPedido> detallesPedidos = detalles.stream().map(DetallesPedidoRequest::toEntity).collect(Collectors.toSet());
        return new Pedido(new Usuario(usuarioCedula), detallesPedidos);
    }
}