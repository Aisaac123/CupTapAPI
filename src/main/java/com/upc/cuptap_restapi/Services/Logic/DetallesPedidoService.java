package com.upc.cuptap_restapi.Services.Logic;

import com.upc.cuptap_restapi.Models.DTO.DTORequest.DetallesPedidoRequest;
import com.upc.cuptap_restapi.Models.Entities.DetallesPedido;
import com.upc.cuptap_restapi.Repositories.DAO.ComboDAO;
import com.upc.cuptap_restapi.Repositories.DAO.DetallesPedidoDAO;
import com.upc.cuptap_restapi.Repositories.DAO.PedidoDAO;
import com.upc.cuptap_restapi.Repositories.DAO.ProductoDAO;
import com.upc.cuptap_restapi.Services.Providers.ProvidersInstances.CRUDServiceInstance;
import com.upc.cuptap_restapi.Services.Providers.Providers.Implements.CService;
import com.upc.cuptap_restapi.Services.Providers.Providers.Implements.DService;
import com.upc.cuptap_restapi.Services.Providers.Providers.Implements.RService;
import com.upc.cuptap_restapi.Services.Providers.Providers.Implements.UService;
import com.upc.cuptap_restapi.Services.Utils.Options.Reconstruct;
import org.springframework.stereotype.Service;

@Service
public class DetallesPedidoService implements CRUDServiceInstance<DetallesPedido, Long>, Reconstruct<DetallesPedido, DetallesPedidoRequest> {

    final
    DetallesPedidoDAO rep;
    private final PedidoDAO pedidoDAO;
    private final ComboDAO comboDAO;
    private final ProductoDAO productoDAO;

    public DetallesPedidoService(DetallesPedidoDAO rep,
                                 PedidoDAO pedidoDAO,
                                 ComboDAO comboDAO,
                                 ProductoDAO productoDAO) {
        this.rep = rep;
        this.pedidoDAO = pedidoDAO;
        this.comboDAO = comboDAO;
        this.productoDAO = productoDAO;
    }

    @Override
    public CService<DetallesPedido, Long> Persist() {
        return new CService<>(rep);
    }

    @Override
    public DService<DetallesPedido, Long> Remove() {
        return new DService<>(rep);
    }

    @Override
    public RService<DetallesPedido, Long> Read() {
        return new RService<>(rep);
    }

    @Override
    public UService<DetallesPedido, Long> Modify() {
        return new UService<>(rep);
    }

    @Override
    public DetallesPedido Reconstruct(DetallesPedidoRequest requestDTO) {
        var detalles = requestDTO.toEntity();
        detalles.setPedido(pedidoDAO.findById(detalles.getPedido().getId()).orElse(null));
        detalles.setCombo(comboDAO.findById(detalles.getCombo().getNombre()).orElse(null));
        detalles.setProducto(productoDAO.findById(detalles.getProducto().getNombre()).orElse(null));
        return detalles;
    }
}
