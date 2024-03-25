package com.upc.cuptap_restapi.Controllers.Controller;


import com.upc.cuptap_restapi.Controllers.DataAccess.DACInstances.CRUDControllerInstance;
import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.CController;
import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.DController;
import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.RController;
import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.UController;
import com.upc.cuptap_restapi.Models.Entities.DetallesPedido;
import com.upc.cuptap_restapi.Models.Entities.Producto;
import com.upc.cuptap_restapi.Services.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/CupTapAPI/v1/Productos")
public class ProductoController implements CRUDControllerInstance<Producto, String> {

    final
    ProductoService serv;

    public ProductoController(ProductoService serv) {
        this.serv = serv;
    }

    @Override
    public CController<Producto, String> Persist() {
        return new CController<>(serv.Persist());
    }

    @Override
    public DController<Producto, String> Remove() {
        return new DController<>(serv.Remove());
    }

    @Override
    public RController<Producto, String> Read() {
        return new RController<>(serv.Read());
    }

    @Override
    public UController<Producto, String> Modify() {
        return new UController<>(serv.Modify());
    }
}
