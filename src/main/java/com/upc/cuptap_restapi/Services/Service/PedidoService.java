package com.upc.cuptap_restapi.Services.Service;

import com.upc.cuptap_restapi.Models.Entities.Pedido;
import com.upc.cuptap_restapi.Repository.DAO.PedidoDAO;
import com.upc.cuptap_restapi.Services.General.GenericServices.CService;
import com.upc.cuptap_restapi.Services.General.GenericServices.DService;
import com.upc.cuptap_restapi.Services.General.GenericServices.RService;
import com.upc.cuptap_restapi.Services.General.GenericServices.UService;
import com.upc.cuptap_restapi.Services.General.Instances.CRUDServiceInstance;
import org.springframework.stereotype.Service;

@Service
public class PedidoService implements CRUDServiceInstance<Pedido, Long> {
    final
    PedidoDAO rep;

    public PedidoService(PedidoDAO rep) {
        this.rep = rep;
    }

    @Override
    public CService<Pedido, Long> Persist() {
        return new CService<>(rep);
    }

    @Override
    public DService<Pedido, Long> Remove() {
        return new DService<>(rep);
    }

    @Override
    public RService<Pedido, Long> Read() {
        return new RService<>(rep);
    }

    @Override
    public UService<Pedido, Long> Modify() {
        return new UService<>(rep);
    }
}
