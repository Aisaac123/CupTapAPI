package com.upc.cuptap_restapi.Services.Bussiness;

import com.upc.cuptap_restapi.Models.Entities.Productos_Combo;
import com.upc.cuptap_restapi.Models.Utilities.Response;
import com.upc.cuptap_restapi.Models.Utilities.ResponseBuilder;
import com.upc.cuptap_restapi.Repositories.DAO.Productos_ComboDAO;
import com.upc.cuptap_restapi.Services.DataAccess.DASIntances.CRUDServiceInstance;
import com.upc.cuptap_restapi.Services.DataAccess.DAServices.Implements.CService;
import com.upc.cuptap_restapi.Services.DataAccess.DAServices.Implements.DService;
import com.upc.cuptap_restapi.Services.DataAccess.DAServices.Implements.RService;
import com.upc.cuptap_restapi.Services.DataAccess.DAServices.Implements.UService;
import org.springframework.stereotype.Service;

@Service
public class Productos_ComboService implements CRUDServiceInstance<Productos_Combo, Long> {
    final
    Productos_ComboDAO rep;

    public Productos_ComboService(Productos_ComboDAO rep) {
        this.rep = rep;
    }

    @Override
    public CService<Productos_Combo, Long> Persist() {
        return new CService<>(rep);
    }

    @Override
    public DService<Productos_Combo, Long> Remove() {
        return new DService<>(rep);
    }

    @Override
    public RService<Productos_Combo, Long> Read() {
        return new RService<>(rep);
    }

    @Override
    public UService<Productos_Combo, Long> Modify() {
        return new UService<>(rep);
    }

    public Response<String> PatchCantidad(Long id, int cant) {
        try {
            Productos_Combo producto = rep.findById(id).orElse(null);
            if (producto != null) {
                int old_cant = producto.getCantidad();
                producto.setCantidad(cant);
                return new ResponseBuilder<String>().withMsg("Operacion Exitosa")
                        .withData("Se ha modificado la cantidad de productos de: " + old_cant + " a: " + producto.getCantidad());
            }
            return ResponseBuilder.Fail("No se ha encontrado el producto del combo a modificar");
        } catch (Exception e) {
            return ResponseBuilder.Error(e);
        }
    }
}
