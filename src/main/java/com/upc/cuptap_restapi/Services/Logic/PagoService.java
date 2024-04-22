package com.upc.cuptap_restapi.Services.Logic;


import com.upc.cuptap_restapi.Models.DTO.DTORequest.PagoRequest;
import com.upc.cuptap_restapi.Models.Entities.Pago;
import com.upc.cuptap_restapi.Repositories.DAO.PagoDAO;
import com.upc.cuptap_restapi.Repositories.DAO.UsuarioDAO;
import com.upc.cuptap_restapi.Services.Providers.ProvidersInstances.CRUDServiceInstance;
import com.upc.cuptap_restapi.Services.Providers.Providers.Implements.CService;
import com.upc.cuptap_restapi.Services.Providers.Providers.Implements.DService;
import com.upc.cuptap_restapi.Services.Providers.Providers.Implements.RService;
import com.upc.cuptap_restapi.Services.Providers.Providers.Implements.UService;
import com.upc.cuptap_restapi.Services.Utils.Options.Reconstruct;
import org.springframework.stereotype.Service;

@Service
public class PagoService implements CRUDServiceInstance<Pago, Long>, Reconstruct<Pago, PagoRequest> {
    final
    PagoDAO rep;
    private final UsuarioDAO usuarioDAO;

    public PagoService(PagoDAO rep,
                       UsuarioDAO usuarioDAO) {
        this.rep = rep;
        this.usuarioDAO = usuarioDAO;
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

    @Override
    public Pago Reconstruct(PagoRequest requestDTO) {
        var pago = requestDTO.toEntity();
        pago.setUsuario(usuarioDAO.findById(pago.getUsuario().getId()).orElse(null));

        return pago;
    }
}
