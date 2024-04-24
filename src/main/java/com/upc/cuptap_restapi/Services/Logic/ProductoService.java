package com.upc.cuptap_restapi.Services.Logic;

import com.upc.cuptap_restapi.Models.Entities.Producto;
import com.upc.cuptap_restapi.Models.Utils.Response;
import com.upc.cuptap_restapi.Models.Utils.ResponseBuilder;
import com.upc.cuptap_restapi.Repositories.DAO.ProductoDAO;
import com.upc.cuptap_restapi.Services.Providers.Providers.Implements.CService;
import com.upc.cuptap_restapi.Services.Providers.Providers.Implements.DService;
import com.upc.cuptap_restapi.Services.Providers.Providers.Implements.RService;
import com.upc.cuptap_restapi.Services.Providers.Providers.Implements.UService;
import com.upc.cuptap_restapi.Services.Providers.ProvidersInstances.CRUDServiceInstance;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProductoService implements CRUDServiceInstance<Producto, String> {

    final
    ProductoDAO rep;

    public ProductoService(ProductoDAO rep) {
        this.rep = rep;
    }

    @Override
    public CService<Producto, String> Persist() {
        return new CService<>(rep);
    }

    @Override
    public DService<Producto, String> Remove() {
        return new DService<>(rep);
    }

    @Override
    public RService<Producto, String> Read() {
        return new RService<>(rep);
    }

    @Override
    public UService<Producto, String> Modify() {
        return new UService<>(rep);
    }

    @Transactional
    public Response AddStock(String nombre, Integer count){
        try {
            rep.sumProductoCantidad(nombre, count);
            return ResponseBuilder.Success("Se ha actualizado la cantidad en stock del producto!");
        }catch (Exception e){
            return ResponseBuilder.Error(e);
        }
    }
    @Transactional
    public Response DeductStock(String nombre, Integer count){
        return  AddStock(nombre, -count);
    }

    @Transactional
    public Response UpdateStock(String nombre, Integer count, String operation){
        switch (operation) {
            case "add" -> {
                return AddStock(nombre, count);
            }
            case "deduct" -> {
                return DeductStock(nombre, count);
            }
            case "update" -> {
                try {
                    rep.updateProductoCantidad(nombre, count);
                    return ResponseBuilder.Success("Se ha actualizado la cantidad en stock del producto!");
                } catch (Exception e) {
                    return ResponseBuilder.Error(e);
                }
            }
            default -> { return ResponseBuilder.Fail("Operacion no valida"); }
        }
    }
    @Transactional
    public Response UpdateStock(Map<String, Integer> requests) {

        try {
            for (var item: requests.keySet()) {
                rep.updateProductoCantidad(item, requests.get(item));
            }
            return ResponseBuilder.Success("Se ha actualizado la cantidad en stock de los productos!");
        } catch (Exception e) {
            return ResponseBuilder.Error(e);
        }
    }
}
