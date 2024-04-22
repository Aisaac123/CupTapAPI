package com.upc.cuptap_restapi.Services.Logic;

import com.upc.cuptap_restapi.Models.DTO.DTORequest.ComboRequest;
import com.upc.cuptap_restapi.Models.Entities.Combo;
import com.upc.cuptap_restapi.Repositories.DAO.ComboDAO;
import com.upc.cuptap_restapi.Repositories.DAO.PromocionDAO;
import com.upc.cuptap_restapi.Services.Providers.ProvidersInstances.CRUDServiceInstance;
import com.upc.cuptap_restapi.Services.Providers.Providers.Implements.CService;
import com.upc.cuptap_restapi.Services.Providers.Providers.Implements.DService;
import com.upc.cuptap_restapi.Services.Providers.Providers.Implements.RService;
import com.upc.cuptap_restapi.Services.Providers.Providers.Implements.UService;
import com.upc.cuptap_restapi.Services.Utils.Options.Reconstruct;
import org.springframework.stereotype.Service;

@Service
public class ComboService implements CRUDServiceInstance<Combo, String>, Reconstruct<Combo, ComboRequest> {

    private final ComboDAO rep;
    private final PromocionDAO promocionDAO;

    public ComboService(ComboDAO rep,
                        PromocionDAO promocionDAO) {
        this.rep = rep;
        this.promocionDAO = promocionDAO;
    }

    @Override
    public CService<Combo, String> Persist() {
        return new CService<>(rep);
    }

    @Override
    public DService<Combo, String> Remove() {
        return new DService<>(rep);
    }

    @Override
    public RService<Combo, String> Read() {
        return new RService<>(rep);
    }

    @Override
    public UService<Combo, String> Modify() {
        return new UService<>(rep);
    }

    @Override
    public Combo Reconstruct(ComboRequest requestDTO) {
        var combo = requestDTO.toEntity();
        if (combo.getPromocion().getId() != null)
            combo.setPromocion(promocionDAO.findById(combo.getPromocion().getId()).orElse(null));
        return combo;
    }
}
