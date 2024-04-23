package com.upc.cuptap_restapi.Models.DTO.DTORequest;

import com.upc.cuptap_restapi.Models.Entities.Pedido;
import com.upc.cuptap_restapi.Models.Interfaces.DTO.RequestDTO;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * DTO for {@link com.upc.cuptap_restapi.Models.Entities.Pedido}
 */
public record PedidoRequest(@NotNull UsuarioDTOPedidoRequest usuario, @NotNull EstadoDTOPedidoRequest estado,
                            @NotNull @Size(min = 1) Set<DetallesPedidoDTOPedidoRequest> detalles) implements Serializable, RequestDTO<Pedido> {
    @Override
    public Pedido toEntity() {
        Set<com.upc.cuptap_restapi.Models.Entities.DetallesPedido> detallesPedidos = detalles.stream().map(DetallesPedidoDTOPedidoRequest::toEntity).collect(Collectors.toSet());
        var estado = new com.upc.cuptap_restapi.Models.Entities.Estado();
        estado.setNombre(estado().nombre);
        return new Pedido(new com.upc.cuptap_restapi.Models.Entities.Usuario(usuario.cedula), estado, detallesPedidos);
    }

    /**
     * DTO for {@link com.upc.cuptap_restapi.Models.Entities.Usuario}
     */
    public record UsuarioDTOPedidoRequest(@NotNull @NotEmpty @NotBlank String cedula) implements Serializable {
    }

    /**
     * DTO for {@link com.upc.cuptap_restapi.Models.Entities.Estado}
     */
    public record EstadoDTOPedidoRequest(@NotNull @NotEmpty @NotBlank String nombre) implements Serializable {
    }

    /**
     * DTO for {@link com.upc.cuptap_restapi.Models.Entities.DetallesPedido}
     */
    public record DetallesPedidoDTOPedidoRequest(@Positive int cantidad, ComboDTOPedidoRequest combo, ProductoDTOPedidoRequest producto) implements Serializable {
        public com.upc.cuptap_restapi.Models.Entities.DetallesPedido toEntity() {
            if (combo == null)
                return new com.upc.cuptap_restapi.Models.Entities.DetallesPedido(cantidad, new com.upc.cuptap_restapi.Models.Entities.Producto(producto().nombre()));
            else
                return new com.upc.cuptap_restapi.Models.Entities.DetallesPedido(cantidad, new com.upc.cuptap_restapi.Models.Entities.Producto(combo.nombre()));
        }

        /**
         * DTO for {@link com.upc.cuptap_restapi.Models.Entities.Combo}
         */
        public record ComboDTOPedidoRequest(@NotNull @NotEmpty @NotBlank String nombre) implements Serializable {
        }

        /**
         * DTO for {@link com.upc.cuptap_restapi.Models.Entities.Producto}
         */
        public record ProductoDTOPedidoRequest(@NotNull @NotEmpty @NotBlank String nombre) implements Serializable {
        }
    }
}