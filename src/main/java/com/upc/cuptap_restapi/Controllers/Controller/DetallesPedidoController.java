package com.upc.cuptap_restapi.Controllers.Controller;


import com.upc.cuptap_restapi.Controllers.DataAccess.DACInstances.CRUDControllerInstance;
import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.CController;
import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.DController;
import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.RController;
import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.UController;
import com.upc.cuptap_restapi.Models.Entities.DetallesPedido;
import com.upc.cuptap_restapi.Services.Service.DetallesPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/CupTapAPI/v1/detalles")
public class DetallesPedidoController implements CRUDControllerInstance<DetallesPedido, Long> {
    final
    DetallesPedidoService serv;

    public DetallesPedidoController(DetallesPedidoService serv) {
        this.serv = serv;
    }

    @Override
    public CController<DetallesPedido, Long> Persist() {
        return new CController<>(serv.Persist());
    }

    @Override
    public DController<DetallesPedido, Long> Remove() {
        return new DController<>(serv.Remove());
    }

    @Override
    public RController<DetallesPedido, Long> Read() {
        return new RController<>(serv.Read());
    }

    @Override
    public UController<DetallesPedido, Long> Modify() {
        return new UController<>(serv.Modify());
    }
}
