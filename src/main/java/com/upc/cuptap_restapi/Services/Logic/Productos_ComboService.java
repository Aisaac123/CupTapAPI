package com.upc.cuptap_restapi.Services.Logic;

import com.upc.cuptap_restapi.Models.Entities.Productos_Combo;
import com.upc.cuptap_restapi.Models.Utils.Response;
import com.upc.cuptap_restapi.Models.Utils.ResponseBuilder;
import com.upc.cuptap_restapi.Repositories.DAO.Productos_ComboDAO;
import com.upc.cuptap_restapi.Services.Providers.Providers.Implements.CService;
import com.upc.cuptap_restapi.Services.Providers.Providers.Implements.DService;
import com.upc.cuptap_restapi.Services.Providers.Providers.Implements.UService;
import com.upc.cuptap_restapi.Services.Providers.ProvidersInstances.CServiceInstance;
import com.upc.cuptap_restapi.Services.Providers.ProvidersInstances.DServiceInstance;
import com.upc.cuptap_restapi.Services.Providers.ProvidersInstances.UServiceInstance;
import org.springframework.stereotype.Service;

@Service
public class Productos_ComboService implements CServiceInstance<Productos_Combo, Long>,
        DServiceInstance<Productos_Combo, Long>,
        UServiceInstance<Productos_Combo, Long> {
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
