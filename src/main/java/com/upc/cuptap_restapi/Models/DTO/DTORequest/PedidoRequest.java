package com.upc.cuptap_restapi.Models.DTO.DTORequest;

import com.upc.cuptap_restapi.Models.Entities.Estado;
import com.upc.cuptap_restapi.Models.Entities.Pedido;
import com.upc.cuptap_restapi.Models.Entities.Usuario;
import com.upc.cuptap_restapi.Models.Interfaces.DTO.RequestDTO;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link com.upc.cuptap_restapi.Models.Entities.Pedido}
 */


public record PedidoRequest(@NotNull String usuarioCedula,
                            @NotNull String estadoNombre) implements Serializable, RequestDTO<Pedido> {
    @Override
    public Pedido toEntity() {
        var estado = new Estado();
        estado.setNombre(estadoNombre);
        return new Pedido(new Usuario(usuarioCedula), estado);
    }
}