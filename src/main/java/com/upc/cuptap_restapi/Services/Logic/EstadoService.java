package com.upc.cuptap_restapi.Services.Logic;

import com.upc.cuptap_restapi.Models.Entities.Estado;
import com.upc.cuptap_restapi.Repositories.DAO.EstadoDAO;
import com.upc.cuptap_restapi.Services.Shared.Implements.RService;
import com.upc.cuptap_restapi.Services.Shared.Instances.RServiceInstance;
import org.springframework.stereotype.Service;

@Service
public class EstadoService implements RServiceInstance<Estado, String> {
    final
    EstadoDAO rep;

    public EstadoService(EstadoDAO rep) {
        this.rep = rep;
    }

    @Override
    public RService<Estado, String> Read() {
        return new RService<>(rep);
    }
}
