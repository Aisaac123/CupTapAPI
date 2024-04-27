package com.upc.cuptap_restapi.Services.Logic;

import com.upc.cuptap_restapi.Models.DTO.DTORequest.PromocionRequest;
import com.upc.cuptap_restapi.Models.Entities.Combo;
import com.upc.cuptap_restapi.Models.Entities.Producto;
import com.upc.cuptap_restapi.Models.Entities.Promocion;
import com.upc.cuptap_restapi.Repositories.DAO.ComboDAO;
import com.upc.cuptap_restapi.Repositories.DAO.ProductoDAO;
import com.upc.cuptap_restapi.Repositories.DAO.PromocionDAO;
import com.upc.cuptap_restapi.Services.Shared.Implements.CService;
import com.upc.cuptap_restapi.Services.Shared.Implements.DService;
import com.upc.cuptap_restapi.Services.Shared.Implements.RService;
import com.upc.cuptap_restapi.Services.Shared.Implements.UService;
import com.upc.cuptap_restapi.Services.Shared.Instances.CRUDServiceInstance;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PromocionService implements CRUDServiceInstance<Promocion, Long> {

    final
    PromocionDAO rep;
    private final ProductoDAO productoDAO;
    private final ComboDAO comboDAO;

    public PromocionService(PromocionDAO rep,
                            ProductoDAO productoDAO,
                            ComboDAO comboDAO) {
        this.rep = rep;
        this.productoDAO = productoDAO;
        this.comboDAO = comboDAO;
    }

    @Override
    public CService<Promocion, Long> Persist() {
        return new CService<>(rep);
    }

    @Override
    public DService<Promocion, Long> Remove() {
        return new DService<>(rep);
    }

    @Override
    public RService<Promocion, Long> Read() {
        return new RService<>(rep);
    }

    @Override
    public UService<Promocion, Long> Modify() {
        return new UService<>(rep);
    }
    public Promocion reconstruct(PromocionRequest requestDTO) {
        var promocion = requestDTO.toEntity();
        Set<Producto> productos = productoDAO
                .findByIds(promocion.getProductos()
                        .stream()
                        .map(Producto::getNombre)
                        .collect(Collectors.toSet()));

        Set<Combo> combos = comboDAO.findByIds(promocion.getCombos()
                .stream()
                .map(Combo::getNombre)
                .collect(Collectors.toSet()));

        productos.forEach(producto -> {
            producto.setPromocion(promocion);
            promocion.AddProducto(producto);
        });
        combos.forEach(combo -> {
            combo.setPromocion(promocion);
            promocion.AddCombos(combo);
        });
        promocion.setProductos(productos);
        promocion.setCombos(combos);
        return promocion;
    }


}
