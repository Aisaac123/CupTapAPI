package com.upc.cuptap_restapi.Services.Service;

import com.upc.cuptap_restapi.Models.Entities.Usuario;
import com.upc.cuptap_restapi.Repository.DAO.UsuarioDAO;
import com.upc.cuptap_restapi.Services.General.GenericServices.CService;
import com.upc.cuptap_restapi.Services.General.GenericServices.DService;
import com.upc.cuptap_restapi.Services.General.GenericServices.RService;
import com.upc.cuptap_restapi.Services.General.GenericServices.UService;
import com.upc.cuptap_restapi.Services.General.Instances.CRUDServiceInstance;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements CRUDServiceInstance<Usuario, String> {

    private final UsuarioDAO rep;

    public UsuarioService(UsuarioDAO repository) {
        rep = repository;
    }

    public boolean getName() {
        return Read().GetAll().isSuccess();
    }

    @Override
    public CService<Usuario, String> Persist() {
        return new CService<>(rep);
    }

    @Override
    public DService<Usuario, String> Remove() {
        return new DService<>(rep);
    }

    @Override
    public RService<Usuario, String> Read() {
        return new RService<>(rep);
    }

    @Override
    public UService<Usuario, String> Modify() {
        return new UService<>(rep);
    }
}

