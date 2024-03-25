package com.upc.cuptap_restapi.Services.Service;

import com.upc.cuptap_restapi.Models.Entities.Productos_Combo;
import com.upc.cuptap_restapi.Repository.DAO.Productos_ComboDAO;
import com.upc.cuptap_restapi.Services.General.GenericServices.CService;
import com.upc.cuptap_restapi.Services.General.GenericServices.DService;
import com.upc.cuptap_restapi.Services.General.GenericServices.RService;
import com.upc.cuptap_restapi.Services.General.GenericServices.UService;
import com.upc.cuptap_restapi.Services.General.Instances.CRUDServiceInstance;
import org.springframework.stereotype.Service;

@Service
public class Productos_ComboService implements CRUDServiceInstance<Productos_Combo, Long> {
    final
    Productos_ComboDAO rep;

    public Productos_ComboService(Productos_ComboDAO rep) {
        this.rep = rep;
    }

    @Override
    public CService<Productos_Combo, Long> Persist() {
        return new CService<>(rep);
    }

    @Override
    public DService<Productos_Combo, Long> Remove() {
        return new DService<>(rep);
    }

    @Override
    public RService<Productos_Combo, Long> Read() {
        return new RService<>(rep);
    }

    @Override
    public UService<Productos_Combo, Long> Modify() {
        return new UService<>(rep);
    }
}
