package com.upc.cuptap_restapi.Services.Service;

import com.upc.cuptap_restapi.Models.Entities.Promocion;
import com.upc.cuptap_restapi.Repositories.DAO.PromocionDAO;
import com.upc.cuptap_restapi.Services.DataAccess.DAServices.Implements.CService;
import com.upc.cuptap_restapi.Services.DataAccess.DAServices.Implements.DService;
import com.upc.cuptap_restapi.Services.DataAccess.DAServices.Implements.RService;
import com.upc.cuptap_restapi.Services.DataAccess.DAServices.Implements.UService;
import com.upc.cuptap_restapi.Services.DataAccess.DASIntances.CRUDServiceInstance;
import org.springframework.stereotype.Service;

@Service
public class PromocionService implements CRUDServiceInstance<Promocion, Long> {

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
}
