package com.upc.cuptap_restapi.Services.Bussiness;

import com.upc.cuptap_restapi.Models.Entities.Admin;
import com.upc.cuptap_restapi.Repositories.DAO.AdminDAO;
import com.upc.cuptap_restapi.Services.DataAccess.DASIntances.RServiceInstance;
import com.upc.cuptap_restapi.Services.DataAccess.DAServices.Implements.RService;
import com.upc.cuptap_restapi.Services.DataAccess.DAServices.Intefaces.Services;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements RServiceInstance<Admin, String>, Services {

    final AdminDAO rep;

    public AdminService(AdminDAO rep) {
        this.rep = rep;
    }

    @Override
    public RService<Admin, String> Read() {
        return new RService<>(rep);
    }


}
