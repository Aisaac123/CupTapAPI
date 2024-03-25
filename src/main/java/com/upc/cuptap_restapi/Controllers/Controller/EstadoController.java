package com.upc.cuptap_restapi.Controllers.Controller;


import com.upc.cuptap_restapi.Controllers.DataAccess.DACInstances.CRUDControllerInstance;
import com.upc.cuptap_restapi.Controllers.DataAccess.DACInstances.RControllerInstance;
import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.RController;
import com.upc.cuptap_restapi.Models.Entities.DetallesPedido;
import com.upc.cuptap_restapi.Models.Entities.Estado;
import com.upc.cuptap_restapi.Services.Service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/CupTapAPI/v1/Estados")
public class EstadoController implements RControllerInstance<Estado, String> {
    final
    EstadoService serv;

    public EstadoController(EstadoService serv) {
        this.serv = serv;
    }


    @Override
    public RController<Estado, String> Read() {
        return new RController<>(serv.Read());
    }
}
