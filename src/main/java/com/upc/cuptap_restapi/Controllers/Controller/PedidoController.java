package com.upc.cuptap_restapi.Controllers.Controller;


import com.upc.cuptap_restapi.Controllers.DataAccess.DACInstances.CRUDControllerInstance;
import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.CController;
import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.DController;
import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.RController;
import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.UController;
import com.upc.cuptap_restapi.Models.Entities.DetallesPedido;
import com.upc.cuptap_restapi.Models.Entities.Pedido;
import com.upc.cuptap_restapi.Services.Service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/CupTapAPI/v1/Pedidos")
public class PedidoController implements CRUDControllerInstance<Pedido, Long> {

    final
    PedidoService serv;

    public PedidoController(PedidoService serv) {
        this.serv = serv;
    }

    @Override
    public CController<Pedido, Long> Persist() {
        return new CController<>(serv.Persist());
    }

    @Override
    public DController<Pedido, Long> Remove() {
        return new DController<>(serv.Remove());
    }

    @Override
    public RController<Pedido, Long> Read() {
        return new RController<>(serv.Read());
    }

    @Override
    public UController<Pedido, Long> Modify() {
        return new UController<>(serv.Modify());
    }
}
