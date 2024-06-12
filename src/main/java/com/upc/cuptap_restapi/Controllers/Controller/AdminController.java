package com.upc.cuptap_restapi.Controllers.Controller;

import com.upc.cuptap_restapi.Services.Logic.AdminService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/Admins")
@Tag(name = "Autenticacion", description = "Controlador del modulo de autenticacion")
public class AdminController {

    AdminService service;

    // TODO Controladores de Autenticacion
}
