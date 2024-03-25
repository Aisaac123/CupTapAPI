package com.upc.cuptap_restapi.Services.Service;


import com.upc.cuptap_restapi.Models.Entities.Pago;
import com.upc.cuptap_restapi.Repository.DAO.PagoDAO;
import com.upc.cuptap_restapi.Services.General.GenericServices.CService;
import com.upc.cuptap_restapi.Services.General.GenericServices.DService;
import com.upc.cuptap_restapi.Services.General.GenericServices.RService;
import com.upc.cuptap_restapi.Services.General.GenericServices.UService;
import com.upc.cuptap_restapi.Services.General.Instances.CRUDServiceInstance;
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
