package com.upc.cuptap_restapi;

import com.upc.cuptap_restapi.Services.Service.AdminService;
import com.upc.cuptap_restapi.Services.Service.UsuarioService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CupTapRestapiApplication {


    UsuarioService usuarioService;

    AdminService adminService;

    public CupTapRestapiApplication(UsuarioService usuarioService, AdminService adminService) {
        this.usuarioService = usuarioService;
        this.adminService = adminService;
    }

    public static void main(String[] args) {
        SpringApplication.run(CupTapRestapiApplication.class, args);

    }
}
