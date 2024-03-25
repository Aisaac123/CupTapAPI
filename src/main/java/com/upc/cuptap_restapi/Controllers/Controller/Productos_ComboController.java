package com.upc.cuptap_restapi.Controllers.Controller;


import com.upc.cuptap_restapi.Controllers.DataAccess.DACInstances.CRUDControllerInstance;
import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.CController;
import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.DController;
import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.RController;
import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.UController;
import com.upc.cuptap_restapi.Models.Entities.DetallesPedido;
import com.upc.cuptap_restapi.Models.Entities.Productos_Combo;
import com.upc.cuptap_restapi.Services.Service.Productos_ComboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/CupTapAPI/v1/ProductosCombos")
public class Productos_ComboController implements CRUDControllerInstance<Productos_Combo, Long> {

    final
    Productos_ComboService serv;

    public Productos_ComboController(Productos_ComboService serv) {
        this.serv = serv;
    }

    @Override
    public CController<Productos_Combo, Long> Persist() {
        return new CController<>(serv.Persist());
    }

    @Override
    public DController<Productos_Combo, Long> Remove() {
        return new DController<>(serv.Remove());
    }

    @Override
    public RController<Productos_Combo, Long> Read() {
        return new RController<>(serv.Read());
    }

    @Override
    public UController<Productos_Combo, Long> Modify() {
        return new UController<>(serv.Modify());
    }
}
