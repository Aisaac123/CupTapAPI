package com.upc.cuptap_restapi.Services.Middlewares.Validations.Requests;

import com.upc.cuptap_restapi.Models.DTO.DTORequest.PedidoRequest;
import com.upc.cuptap_restapi.Models.DTO.DTORequest.PedidoRequestNoCedula;
import com.upc.cuptap_restapi.Models.Entities.DetallesPedido;
import com.upc.cuptap_restapi.Models.Entities.Pedido;
import com.upc.cuptap_restapi.Models.Utils.Response;
import com.upc.cuptap_restapi.Models.Utils.ResponseBuilder;
import com.upc.cuptap_restapi.Repositories.DAO.DetallesPedidoDAO;
import org.springframework.stereotype.Service;

@Service
public class PedidoRequestValidations {

    final DetallesPedidoDAO detallesPedidoDAO;
    public PedidoRequestValidations(DetallesPedidoDAO detallesPedidoDAO) {
        this.detallesPedidoDAO = detallesPedidoDAO;
    }

    public Response<Boolean> Validate(PedidoRequest pedido) {
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
    public Response<Boolean> Validate(PedidoRequestNoCedula pedido) {
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

    public Response<Boolean> Validate(PedidoRequestNoCedula.DetallesPedidoDTOPedidoRequestNoCedula item) {
            if (item.combo() != null && item.producto() != null) return ResponseBuilder
                    .Fail("El detalle de la posicion posee un combo y un producto a la vez " +
                            " --Nombre del producto: " + item.producto().nombre() +" "+
                            " --Nombre del combo: " + item.combo().nombre() +" ");
        return ResponseBuilder.Success();
    }

    public Response<Boolean> Validate(PedidoRequest.DetallesPedidoDTOPedidoRequest item) {
        if (item.combo() != null && item.producto() != null) return ResponseBuilder
                .Fail("El detalle posee un combo y un producto a la vez " +
                        " --Nombre del producto: " + item.producto().nombre() +" "+
                        " --Nombre del combo: " + item.combo().nombre() +" ");
        return ResponseBuilder.Success();
    }
    public Response<Boolean> Validate(DetallesPedido detalle) {
        if (detalle.getCombo() != null && detalle.getProducto() != null) return ResponseBuilder
                .Fail("El detalle posee un combo y un producto a la vez " +
                        " --Nombre del producto: " + detalle.getProducto().getNombre() +" "+
                        " --Nombre del combo: " + detalle.getCombo().getNombre() +" ");
        if (detalle.getProducto() != null && detalle.getProducto().getNombre() != null) {
            detalle.setCombo(null);
            if (!detallesPedidoDAO.hasStockForPedido(detalle.getProducto().getNombre(), detalle.getCantidad()))
                return ResponseBuilder.Fail("No hay suficiente productos en stock de: " + detalle.getProducto().getNombre() + " para este pedido");

        }
        else if (detalle.getCombo() != null && detalle.getCombo().getNombre() != null) {
            detalle.setPedido(null);
            if (!detallesPedidoDAO.hasStockForCombos(detalle.getCombo().getNombre(), detalle.getCantidad()))
                return ResponseBuilder.Fail("No hay suficiente combos en stock de: " + detalle.getProducto().getNombre()+ " para este pedido");
        }
        return ResponseBuilder.Success();
    }

}
