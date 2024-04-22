package com.upc.cuptap_restapi.Services.Logic;

import com.upc.cuptap_restapi.Models.DTO.DTORequest.ProductoRequest;
import com.upc.cuptap_restapi.Models.Entities.Producto;
import com.upc.cuptap_restapi.Repositories.DAO.ProductoDAO;
import com.upc.cuptap_restapi.Repositories.DAO.PromocionDAO;
import com.upc.cuptap_restapi.Services.Providers.ProvidersInstances.CRUDServiceInstance;
import com.upc.cuptap_restapi.Services.Providers.Providers.Implements.CService;
import com.upc.cuptap_restapi.Services.Providers.Providers.Implements.DService;
import com.upc.cuptap_restapi.Services.Providers.Providers.Implements.RService;
import com.upc.cuptap_restapi.Services.Providers.Providers.Implements.UService;
import com.upc.cuptap_restapi.Services.Utils.Options.Reconstruct;
import org.springframework.stereotype.Service;

@Service
public class ProductoService implements CRUDServiceInstance<Producto, String>, Reconstruct<Producto, ProductoRequest> {

    final
    ProductoDAO rep;
    private final PromocionDAO promocionDAO;

    public ProductoService(ProductoDAO rep,
                           PromocionDAO promocionDAO) {
        this.rep = rep;
        this.promocionDAO = promocionDAO;
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

    @Override
    public Producto Reconstruct(ProductoRequest requestDTO) {
        var producto = requestDTO.toEntity();
        if (producto.getPromocion() != null && producto.getPromocion().getId() != null)
            producto.setPromocion(promocionDAO.findById(producto.getPromocion().getId()).orElse(null));
        return producto;
    }
}
