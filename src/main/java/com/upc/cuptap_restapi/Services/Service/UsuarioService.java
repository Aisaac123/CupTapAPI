package com.upc.cuptap_restapi.Services.Service;

import com.upc.cuptap_restapi.Models.Entities.Usuario;
import com.upc.cuptap_restapi.Repositories.DAO.UsuarioDAO;
import com.upc.cuptap_restapi.Services.DataAccess.DASIntances.CRUDServiceInstance;
import com.upc.cuptap_restapi.Services.DataAccess.DAServices.Implements.CService;
import com.upc.cuptap_restapi.Services.DataAccess.DAServices.Implements.DService;
import com.upc.cuptap_restapi.Services.DataAccess.DAServices.Implements.RService;
import com.upc.cuptap_restapi.Services.DataAccess.DAServices.Implements.UService;
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

