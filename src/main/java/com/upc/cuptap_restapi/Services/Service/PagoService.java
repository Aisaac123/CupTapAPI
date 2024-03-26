package com.upc.cuptap_restapi.Services.Service;


import com.upc.cuptap_restapi.Models.Entities.Pago;
import com.upc.cuptap_restapi.Repositories.DAO.PagoDAO;
import com.upc.cuptap_restapi.Services.DataAccess.DASIntances.CRUDServiceInstance;
import com.upc.cuptap_restapi.Services.DataAccess.DAServices.Implements.CService;
import com.upc.cuptap_restapi.Services.DataAccess.DAServices.Implements.DService;
import com.upc.cuptap_restapi.Services.DataAccess.DAServices.Implements.RService;
import com.upc.cuptap_restapi.Services.DataAccess.DAServices.Implements.UService;
import org.springframework.stereotype.Service;

@Service
public class PagoService implements CRUDServiceInstance<Pago, Long> {
    final
    PagoDAO rep;

    public PagoService(PagoDAO rep) {
        this.rep = rep;
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
}
