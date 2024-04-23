package com.upc.cuptap_restapi.Models.DTO.DTORequest;

import com.upc.cuptap_restapi.Models.Entities.Pedido;
import com.upc.cuptap_restapi.Models.Interfaces.DTO.RequestDTO;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * DTO for {@link Pedido}
 */
public record PedidoRequestNoCedula(@NotNull EstadoDTOPedidoRequestNoCedula estado,
                                    @NotNull @Size(min = 1) Set<DetallesPedidoDTOPedidoRequestNoCedula> detalles) implements Serializable, RequestDTO<Pedido> {

    @Override
    public Pedido toEntity() {
        Set<com.upc.cuptap_restapi.Models.Entities.DetallesPedido> detallesPedidos = detalles.stream().map(DetallesPedidoDTOPedidoRequestNoCedula::toEntity).collect(Collectors.toSet());
        var estado = new com.upc.cuptap_restapi.Models.Entities.Estado();
        estado.setNombre(estado().nombre);
        return new Pedido(new com.upc.cuptap_restapi.Models.Entities.Usuario(), estado, detallesPedidos);
    }

    public Pedido toEntity(String cedula) {
        Set<com.upc.cuptap_restapi.Models.Entities.DetallesPedido> detallesPedidos = detalles.stream().map(DetallesPedidoDTOPedidoRequestNoCedula::toEntity).collect(Collectors.toSet());
        var estado = new com.upc.cuptap_restapi.Models.Entities.Estado();
        estado.setNombre(estado().nombre);
        return new Pedido(new com.upc.cuptap_restapi.Models.Entities.Usuario(cedula), estado, detallesPedidos);
    }

    /**
     * DTO for {@link com.upc.cuptap_restapi.Models.Entities.Estado}
     */
    public record EstadoDTOPedidoRequestNoCedula(@NotNull @NotEmpty @NotBlank String nombre) implements Serializable {
    }

    /**
     * DTO for {@link com.upc.cuptap_restapi.Models.Entities.DetallesPedido}
     */
    public record DetallesPedidoDTOPedidoRequestNoCedula(@Positive int cantidad, ComboDTOPedidoRequestNoCedula combo, ProductoDTOPedidoRequestNoCedula producto) implements Serializable {
        public com.upc.cuptap_restapi.Models.Entities.DetallesPedido toEntity() {
            if (combo == null)
                return new com.upc.cuptap_restapi.Models.Entities.DetallesPedido(cantidad, new com.upc.cuptap_restapi.Models.Entities.Producto(producto().nombre()));
            else
                return new com.upc.cuptap_restapi.Models.Entities.DetallesPedido(cantidad, new com.upc.cuptap_restapi.Models.Entities.Producto(combo.nombre()));
        }

        /**
         * DTO for {@link com.upc.cuptap_restapi.Models.Entities.Combo}
         */
        public record ComboDTOPedidoRequestNoCedula(@NotNull @NotEmpty @NotBlank String nombre) implements Serializable {
        }

        /**
         * DTO for {@link com.upc.cuptap_restapi.Models.Entities.Producto}
         */
        public record ProductoDTOPedidoRequestNoCedula(@NotNull @NotEmpty @NotBlank String nombre) implements Serializable {
        }
    }
}