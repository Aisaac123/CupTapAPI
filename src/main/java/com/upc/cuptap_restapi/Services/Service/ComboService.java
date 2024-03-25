package com.upc.cuptap_restapi.Services.Service;

import com.upc.cuptap_restapi.Models.Entities.Combo;
import com.upc.cuptap_restapi.Repositories.DAO.ComboDAO;
import com.upc.cuptap_restapi.Services.DataAccess.DAServices.Implements.CService;
import com.upc.cuptap_restapi.Services.DataAccess.DAServices.Implements.DService;
import com.upc.cuptap_restapi.Services.DataAccess.DAServices.Implements.RService;
import com.upc.cuptap_restapi.Services.DataAccess.DAServices.Implements.UService;
import com.upc.cuptap_restapi.Services.DataAccess.DASIntances.CRUDServiceInstance;
import org.springframework.stereotype.Service;

@Service
public class ComboService implements CRUDServiceInstance<Combo, String> {

    private final ComboDAO rep;

    public ComboService(ComboDAO rep) {
        this.rep = rep;
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
}
