package com.upc.cuptap_restapi.Services.Logic;

import com.upc.cuptap_restapi.Repositories.DAO.AdminDAO;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    final AdminDAO rep;

    public AdminService(AdminDAO rep) {
        this.rep = rep;
    }


}
