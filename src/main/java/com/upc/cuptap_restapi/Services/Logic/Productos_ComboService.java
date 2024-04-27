package com.upc.cuptap_restapi.Services.Logic;

import com.upc.cuptap_restapi.Models.DTO.DTORequest.Productos_ComboRequest;
import com.upc.cuptap_restapi.Models.Entities.Productos_Combo;
import com.upc.cuptap_restapi.Models.Utils.Response;
import com.upc.cuptap_restapi.Models.Utils.ResponseBuilder;
import com.upc.cuptap_restapi.Repositories.DAO.ComboDAO;
import com.upc.cuptap_restapi.Repositories.DAO.ProductoDAO;
import com.upc.cuptap_restapi.Repositories.DAO.Productos_ComboDAO;
import com.upc.cuptap_restapi.Services.Shared.Implements.CService;
import com.upc.cuptap_restapi.Services.Shared.Implements.DService;
import com.upc.cuptap_restapi.Services.Shared.Implements.UService;
import com.upc.cuptap_restapi.Services.Shared.Instances.CServiceInstance;
import com.upc.cuptap_restapi.Services.Shared.Instances.DServiceInstance;
import com.upc.cuptap_restapi.Services.Shared.Instances.UServiceInstance;
import org.springframework.stereotype.Service;

@Service
public class Productos_ComboService implements CServiceInstance<Productos_Combo, Long>,
        DServiceInstance<Productos_Combo, Long>,
        UServiceInstance<Productos_Combo, Long> {
    final
    Productos_ComboDAO rep;
    private final ComboDAO comboDAO;
    private final ProductoDAO productoDAO;

    public Productos_ComboService(Productos_ComboDAO rep,
                                  ComboDAO comboDAO,
                                  ProductoDAO productoDAO) {
        this.rep = rep;
        this.comboDAO = comboDAO;
        this.productoDAO = productoDAO;
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

    /**
     Reconstruct of {@link Productos_Combo}
     */
    public Productos_Combo reconstruct(Productos_ComboRequest requestDTO) {
        var p_combos = requestDTO.toEntity();

        p_combos.setCombo(comboDAO.findById(p_combos.getCombo().getNombre()).orElse(null));
        p_combos.setProducto(productoDAO.findById(p_combos.getProducto().getNombre()).orElse(null));

        return p_combos;
    }
}
