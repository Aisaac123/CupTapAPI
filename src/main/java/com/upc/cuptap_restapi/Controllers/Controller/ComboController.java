package com.upc.cuptap_restapi.Controllers.Controller;

import com.upc.cuptap_restapi.Controllers.DataAccess.DACInstances.CRUDControllerInstance;
import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.CController;
import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.DController;
import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.RController;
import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.UController;
import com.upc.cuptap_restapi.Models.Entities.Combo;
import com.upc.cuptap_restapi.Services.Service.ComboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/CupTapAPI/v1/Combos")
public class ComboController implements CRUDControllerInstance<Combo, String> {

    final
    ComboService serv;

    public ComboController(ComboService serv) {
        this.serv = serv;
    }

    @Override
    public CController<Combo, String> Persist() {
        return new CController<>(serv.Persist());
    }

    @Override
    public DController<Combo, String> Remove() {
        return new DController<>(serv.Remove());
    }

    @Override
    public RController<Combo, String> Read() {
        return new RController<>(serv.Read());
    }

    @Override
    public UController<Combo, String> Modify() {
        return new UController<>(serv.Modify());
    }
}
