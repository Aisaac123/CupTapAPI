package com.upc.cuptap_restapi.Controllers.Controller;

import com.upc.cuptap_restapi.Controllers.DataAccess.DACInstances.RControllerInstance;
import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.RController;
import com.upc.cuptap_restapi.Models.Entities.Admin;
import com.upc.cuptap_restapi.Models.Utilities.Response;
import com.upc.cuptap_restapi.Services.Service.AdminService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/CupTapAPI/v1/Admins")
@Tag(name = "Administradores", description = "Controlador del modulo de administradores")
public class AdminController implements RControllerInstance<Admin, String> {

    AdminService service;

    public AdminController(AdminService service) {
        this.service = service;
    }

    @Override
    public RController<Admin, String> Read() {
        return new RController<>(service.Read());
    }

    @GetMapping("")
    public ResponseEntity<Response<List<Admin>>> GetAll() {
        return Read().GetAll();
    }
}
