package com.upc.cuptap_restapi.Services.Service;

import com.upc.cuptap_restapi.Models.Entities.Admin;
import com.upc.cuptap_restapi.Repository.DAO.AdminDAO;
import com.upc.cuptap_restapi.Services.General.GenericServices.RService;
import com.upc.cuptap_restapi.Services.General.Instances.RServiceInstance;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements RServiceInstance<Admin, String> {

    final AdminDAO rep;

    public AdminService(AdminDAO rep) {
        this.rep = rep;
    }

    @Override
    public RService<Admin, String> Read() {
        return new RService<>(rep);
    }
}