package com.upc.cuptap_restapi.Controllers.Controller;

import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.CController;
import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.DController;
import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.RController;
import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.UController;
import com.upc.cuptap_restapi.Controllers.DataAccess.DACInstances.CRUDControllerInstance;
import com.upc.cuptap_restapi.Models.Entities.Usuario;
import com.upc.cuptap_restapi.Models.Utilities.Response;
import com.upc.cuptap_restapi.Services.Service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/CupTapAPI/v1/Usuarios")
@Tag(name = "Usuarios", description = "Controlador de metodos para administradores")

public class UsuarioController implements CRUDControllerInstance<Usuario, String> {


    private final UsuarioService service;

    public UsuarioController(UsuarioService usuarioService) {
        service = usuarioService;
    }

    @Override
    public CController<Usuario, String> Persist() {
        return new CController<>(service.Persist());
    }

    @Override
    public DController<Usuario, String> Remove() {
        return new DController<>(service.Remove());
    }

    @Override
    public RController<Usuario, String> Read() {
        return new RController<>(service.Read());
    }

    @Override
    public UController<Usuario, String> Modify() {
        return new UController<>(service.Modify());
    }

    @GetMapping("")
    public ResponseEntity<Response<List<Usuario>>> GetAll() {
        return Read().GetAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Usuario>> GetById(@PathVariable String id) {
        return Read().GetById(id);
    }
}
