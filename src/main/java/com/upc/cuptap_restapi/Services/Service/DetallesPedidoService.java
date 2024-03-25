package com.upc.cuptap_restapi.Services.Service;

import com.upc.cuptap_restapi.Models.Entities.DetallesPedido;
import com.upc.cuptap_restapi.Repositories.DAO.DetallesPedidoDAO;
import com.upc.cuptap_restapi.Services.DataAccess.DAServices.Implements.CService;
import com.upc.cuptap_restapi.Services.DataAccess.DAServices.Implements.DService;
import com.upc.cuptap_restapi.Services.DataAccess.DAServices.Implements.RService;
import com.upc.cuptap_restapi.Services.DataAccess.DAServices.Implements.UService;
import com.upc.cuptap_restapi.Services.DataAccess.DASIntances.CRUDServiceInstance;
import org.springframework.stereotype.Service;

@Service
public class DetallesPedidoService implements CRUDServiceInstance<DetallesPedido, Long> {

    final
    DetallesPedidoDAO rep;

    public DetallesPedidoService(DetallesPedidoDAO rep) {
        this.rep = rep;
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
}
