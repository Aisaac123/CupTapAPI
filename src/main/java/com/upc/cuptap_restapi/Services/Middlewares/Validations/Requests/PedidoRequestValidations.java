package com.upc.cuptap_restapi.Services.Middlewares.Validations.Requests;

import com.upc.cuptap_restapi.Models.DTO.DTORequest.PedidoRequest;
import com.upc.cuptap_restapi.Models.DTO.DTORequest.PedidoRequestNoCedula;
import com.upc.cuptap_restapi.Models.Entities.DetallesPedido;
import com.upc.cuptap_restapi.Models.Entities.Pedido;
import com.upc.cuptap_restapi.Models.Utils.Response;
import com.upc.cuptap_restapi.Models.Utils.ResponseBuilder;
import com.upc.cuptap_restapi.Repositories.DAO.DetallesPedidoDAO;
import org.springframework.stereotype.Service;

public class PedidoRequestValidations {



    public static Response<Boolean> Validate(PedidoRequest pedido) {
        int i = 0;
        for (var item : pedido.detalles()) {
            if (item.combo() != null && item.producto() != null) return ResponseBuilder
                    .Fail("El detalle de la posicion #" + i + " posee un combo y un producto a la vez \n" +
                            "Detalle #" + i + ": " +
                            "\nNombre del producto: " + item.producto().nombre() +"\n" +
                            "\nNombre del combo: " + item.combo().nombre() +"\n");
            i++;
        }
        return ResponseBuilder.Success();
    }
    public static Response<Boolean> Validate(PedidoRequestNoCedula pedido) {
        int i = 0;
        for (var item : pedido.detalles()) {
            if (item.combo() != null && item.producto() != null) return ResponseBuilder
                    .Fail("El detalle de la posicion #" + i + " posee un combo y un producto a la vez " +
                            "-Detalle #" + i + ": " +
                            " --Nombre del producto: " + item.producto().nombre() +" "+
                            " --Nombre del combo: " + item.combo().nombre() +" ");

            i++;
        }
        return ResponseBuilder.Success();
    }

    public static Response<Boolean> Validate(PedidoRequestNoCedula.DetallesPedidoDTOPedidoRequestNoCedula item) {
            if (item.combo() != null && item.producto() != null) return ResponseBuilder
                    .Fail("El detalle de la posicion posee un combo y un producto a la vez " +
                            " --Nombre del producto: " + item.producto().nombre() +" "+
                            " --Nombre del combo: " + item.combo().nombre() +" ");
        return ResponseBuilder.Success();
    }

    public static Response<Boolean> Validate(PedidoRequest.DetallesPedidoDTOPedidoRequest item) {
        if (item.combo() != null && item.producto() != null) return ResponseBuilder
                .Fail("El detalle posee un combo y un producto a la vez " +
                        " --Nombre del producto: " + item.producto().nombre() +" "+
                        " --Nombre del combo: " + item.combo().nombre() +" ");
        return ResponseBuilder.Success();
    }
    public static Response<Boolean> Validate(DetallesPedido item) {
        if (item.getCombo() != null && item.getProducto() != null) return ResponseBuilder
                .Fail("El detalle posee un combo y un producto a la vez " +
                        " --Nombre del producto: " + item.getProducto().getNombre() +" "+
                        " --Nombre del combo: " + item.getCombo().getNombre() +" ");
        return ResponseBuilder.Success();
    }

}
