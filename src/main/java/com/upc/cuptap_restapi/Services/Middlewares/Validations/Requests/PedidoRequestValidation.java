package com.upc.cuptap_restapi.Services.Middlewares.Validations.Requests;

import com.upc.cuptap_restapi.Models.DTO.DTORequest.DetallesPedidoRequest;

public class PedidoRequestValidation {
    public static boolean Validate(DetallesPedidoRequest detallesPedido) {
        return !(detallesPedido.comboNombre() != null && detallesPedido.productoNombre() != null);
    }
}
