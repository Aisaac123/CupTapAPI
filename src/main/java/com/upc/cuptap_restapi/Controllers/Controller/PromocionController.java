package com.upc.cuptap_restapi.Controllers.Controller;

import com.upc.cuptap_restapi.Controllers.DataAccess.DACInstances.CRUDControllerInstance;
import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.CController;
import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.DController;
import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.RController;
import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.UController;
import com.upc.cuptap_restapi.Models.Entities.DetallesPedido;
import com.upc.cuptap_restapi.Models.Entities.Promocion;
import com.upc.cuptap_restapi.Services.Service.PromocionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/CupTapAPI/v1/Promociones")
public class PromocionController implements CRUDControllerInstance<Promocion, Long> {

    final
    PromocionService serv;

    public PromocionController(PromocionService serv) {
        this.serv = serv;
    }

    @Override
    public CController<Promocion, Long> Persist() {
        return new CController<>(serv.Persist());
    }

    @Override
    public DController<Promocion, Long> Remove() {
        return new DController<>(serv.Remove());
    }

    @Override
    public RController<Promocion, Long> Read() {
        return new RController<>(serv.Read());
    }

    @Override
    public UController<Promocion, Long> Modify() {
        return new UController<>(serv.Modify());
    }
}
