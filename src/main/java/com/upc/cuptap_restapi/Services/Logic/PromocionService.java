package com.upc.cuptap_restapi.Services.Logic;

import com.upc.cuptap_restapi.Models.DTO.DTORequest.PromocionRequest;
import com.upc.cuptap_restapi.Models.Entities.Promocion;
import com.upc.cuptap_restapi.Repositories.DAO.PromocionDAO;
import com.upc.cuptap_restapi.Services.Providers.ProvidersInstances.CRUDServiceInstance;
import com.upc.cuptap_restapi.Services.Providers.Providers.Implements.CService;
import com.upc.cuptap_restapi.Services.Providers.Providers.Implements.DService;
import com.upc.cuptap_restapi.Services.Providers.Providers.Implements.RService;
import com.upc.cuptap_restapi.Services.Providers.Providers.Implements.UService;
import com.upc.cuptap_restapi.Services.Utils.Options.Reconstruct;
import org.springframework.stereotype.Service;

@Service
public class PromocionService implements CRUDServiceInstance<Promocion, Long>, Reconstruct<Promocion, PromocionRequest> {

    final
    PromocionDAO rep;

    public PromocionService(PromocionDAO rep) {
        this.rep = rep;
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

    @Override
    public Promocion Reconstruct(PromocionRequest requestDTO) {
        return requestDTO.toEntity();
    }
}