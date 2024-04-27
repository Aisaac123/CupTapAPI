package com.upc.cuptap_restapi.Services.Validators;

import com.upc.cuptap_restapi.Models.Entities.DetallesPedido;
import com.upc.cuptap_restapi.Models.Utils.Response;
import com.upc.cuptap_restapi.Models.Utils.ResponseBuilder;
import com.upc.cuptap_restapi.Repositories.DAO.DetallesPedidoDAO;
import org.springframework.stereotype.Service;

@Service
public class DetallePedidoValidator {

    final DetallesPedidoDAO detallesPedidoDAO;
    public DetallePedidoValidator(DetallesPedidoDAO detallesPedidoDAO) {
        this.detallesPedidoDAO = detallesPedidoDAO;
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
