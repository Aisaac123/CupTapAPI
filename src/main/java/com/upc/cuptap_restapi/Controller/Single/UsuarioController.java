package com.upc.cuptap_restapi.Controller.Single;

import com.upc.cuptap_restapi.Controller.General.Implementation.CController;
import com.upc.cuptap_restapi.Controller.General.Implementation.DController;
import com.upc.cuptap_restapi.Controller.General.Implementation.RController;
import com.upc.cuptap_restapi.Controller.General.Implementation.UController;
import com.upc.cuptap_restapi.Controller.Instances.CRUDControllerInstance;
import com.upc.cuptap_restapi.Models.Entities.Usuario;
import com.upc.cuptap_restapi.Models.Utilities.Response;
import com.upc.cuptap_restapi.Services.Service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/CupTapAPI/Usuarios")
public class UsuarioController implements CRUDControllerInstance<Usuario, String> {


    private final UsuarioService service;

    public UsuarioController(UsuarioService usuarioService) {
        service = usuarioService;
    }

    @Override
    public CController<Usuario, String> Persist() {
        return new CController<Usuario, String>().setService(service);
    }

    @Override
    public DController<Usuario, String> Remove() {
        return new DController<Usuario, String>().setService(service);
    }

    @Override
    public RController<Usuario, String> Read() {
        return new RController<Usuario, String>().setService(service);
    }

    @Override
    public UController<Usuario, String> Modify() {
        return new UController<Usuario, String>().setService(service);
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
