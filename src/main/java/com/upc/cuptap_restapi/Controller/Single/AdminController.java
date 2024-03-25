package com.upc.cuptap_restapi.Controller.Single;

import com.upc.cuptap_restapi.Controller.General.Implementation.RController;
import com.upc.cuptap_restapi.Models.Entities.Admin;
import com.upc.cuptap_restapi.Services.Service.AdminService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/CupTapAPI/Admins")
public class AdminController extends RController<Admin, String> {

    public AdminController(AdminService serv) {
        super.setService(serv);
    }

}
