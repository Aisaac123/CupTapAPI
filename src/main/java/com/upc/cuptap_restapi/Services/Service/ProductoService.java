package com.upc.cuptap_restapi.Services.Service;

import com.upc.cuptap_restapi.Models.Entities.Producto;
import com.upc.cuptap_restapi.Repositories.DAO.ProductoDAO;
import com.upc.cuptap_restapi.Services.DataAccess.DAServices.Implements.CService;
import com.upc.cuptap_restapi.Services.DataAccess.DAServices.Implements.DService;
import com.upc.cuptap_restapi.Services.DataAccess.DAServices.Implements.RService;
import com.upc.cuptap_restapi.Services.DataAccess.DAServices.Implements.UService;
import com.upc.cuptap_restapi.Services.DataAccess.DASIntances.CRUDServiceInstance;
import org.springframework.stereotype.Service;

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
}
