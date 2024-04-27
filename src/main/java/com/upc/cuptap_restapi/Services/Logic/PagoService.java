package com.upc.cuptap_restapi.Services.Logic;


import com.upc.cuptap_restapi.Models.DTO.DTORequest.PagoRequest;
import com.upc.cuptap_restapi.Models.Entities.Pago;
import com.upc.cuptap_restapi.Repositories.DAO.PagoDAO;
import com.upc.cuptap_restapi.Repositories.DAO.PedidoDAO;
import com.upc.cuptap_restapi.Services.Shared.Implements.CService;
import com.upc.cuptap_restapi.Services.Shared.Implements.DService;
import com.upc.cuptap_restapi.Services.Shared.Implements.RService;
import com.upc.cuptap_restapi.Services.Shared.Implements.UService;
import com.upc.cuptap_restapi.Services.Shared.Instances.CRUDServiceInstance;
import org.springframework.stereotype.Service;

@Service
public class PagoService implements CRUDServiceInstance<Pago, Long> {
    final
    PagoDAO rep;
    private final PedidoDAO pedidoDAO;

    public PagoService(PagoDAO rep,
                       PedidoDAO pedidoDAO) {
        this.rep = rep;
        this.pedidoDAO = pedidoDAO;
    }

    @Override
    public CService<Pago, Long> Persist() {
        return new CService<>(rep);
    }

    @Override
    public DService<Pago, Long> Remove() {
        return new DService<>(rep);
    }

    @Override
    public RService<Pago, Long> Read() {
        return new RService<>(rep);
    }

    @Override
    public UService<Pago, Long> Modify() {
        return new UService<>(rep);
    }

    /**
     Reconstruct of {@link Pago}
     */
    public Pago reconstruct(PagoRequest requestDTO) {
        var pago = requestDTO.toEntity();
        pago.setPedido(pedidoDAO.findById(pago.getPedido().getId()).orElse(null));

        return pago;
    }
}
