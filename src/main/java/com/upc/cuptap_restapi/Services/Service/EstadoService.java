package com.upc.cuptap_restapi.Services.Service;

import com.upc.cuptap_restapi.Models.Entities.Estado;
import com.upc.cuptap_restapi.Repository.DAO.EstadoDAO;
import com.upc.cuptap_restapi.Services.General.GenericServices.RService;
import com.upc.cuptap_restapi.Services.General.Instances.RServiceInstance;
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
