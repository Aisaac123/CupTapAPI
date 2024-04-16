package com.upc.cuptap_restapi.Services.Bussiness;

import com.upc.cuptap_restapi.Models.Entities.Pedido;
import com.upc.cuptap_restapi.Models.Entities.Usuario;
import com.upc.cuptap_restapi.Models.Utilities.Response;
import com.upc.cuptap_restapi.Models.Utilities.ResponseBuilder;
import com.upc.cuptap_restapi.Repositories.DAO.EstadoDAO;
import com.upc.cuptap_restapi.Repositories.DAO.UsuarioDAO;
import com.upc.cuptap_restapi.Services.DataAccess.DASIntances.CRUDServiceInstance;
import com.upc.cuptap_restapi.Services.DataAccess.DAServices.Implements.CService;
import com.upc.cuptap_restapi.Services.DataAccess.DAServices.Implements.DService;
import com.upc.cuptap_restapi.Services.DataAccess.DAServices.Implements.RService;
import com.upc.cuptap_restapi.Services.DataAccess.DAServices.Implements.UService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsuarioService implements CRUDServiceInstance<Usuario, UUID> {

    private final UsuarioDAO rep;
    private final EstadoDAO estadoDAO;

    public UsuarioService(UsuarioDAO repository,
                          EstadoDAO estadoDAO) {
        rep = repository;
        this.estadoDAO = estadoDAO;
    }

    public boolean getName() {
        return Read().GetAll().isSuccess();
    }

    @Override
    public CService<Usuario, UUID> Persist() {
        return new CService<>(rep);
    }

    @Override
    public DService<Usuario, UUID> Remove() {
        return new DService<>(rep);
    }

    @Override
    public RService<Usuario, UUID> Read() {
        return new RService<>(rep);
    }

    @Override
    public UService<Usuario, UUID> Modify() {
        return new UService<>(rep);
    }

    public Response<Usuario> DeleteByCedula(String cedula) {
        try {
            Usuario user = rep.findByCedula(cedula);
            rep.delete(user);
            return new ResponseBuilder<Usuario>().withData(user).withMsg("Se ha eliminado exitosamente");
        } catch (Exception e) {
            return ResponseBuilder.Error(e);
        }
    }

    public Response<Map<String,Usuario>> UpdateByCedula(Usuario entity, String cedula) {
        try {
            Usuario user = rep.findByCedula(cedula);
            if (user !=null) {
                Usuario oldEnt = (Usuario) user.cloneEntity();
                user.update(entity);
                HashMap<String, Usuario> map = new HashMap<>();
                map.put("old", oldEnt);
                map.put("new", user);
                rep.save(user);
                return new ResponseBuilder<Map<String, Usuario>>().withData(map);
            }
            return ResponseBuilder.Fail("No se ha encontrado el item a actualizar");

        } catch (Exception e) {
            return ResponseBuilder.Error(e);
        }
    }

    public Response<Usuario> GetByCedula(String cedula) {
        try {
            Usuario user = rep.findByCedula(cedula);
            if (user == null){ return ResponseBuilder.Fail("No se ha encontrado el usuario por su cedula"); }
            return new ResponseBuilder<Usuario>().withSuccess(true).withData(user);
        } catch (Exception e) {
            return ResponseBuilder.Error(e);
        }
    }
    public Response<List<Pedido>> GetPedidos(String cedula) {
        try {
            Usuario user = rep.findByCedula(cedula);
            if (user == null){ return ResponseBuilder.Fail("No se ha encontrado el usuario por su cedula"); }
            if (user.getPedidos() == null || user.getPedidos().isEmpty())
                return new ResponseBuilder<List<Pedido>>().withSuccess(true).withMsg("Lista de pedidos vacia").withData(new ArrayList<>());
            return new ResponseBuilder<List<Pedido>>().withSuccess(true).withData(user.getPedidos());
        } catch (Exception e) {
            return ResponseBuilder.Error(e);
        }
    }

    public Response<Pedido> AddPedidoToUsuario(String cedula, Pedido pedido) {
        try {
            Usuario user = rep.findByCedula(cedula);
            pedido.setUsuario(user);
            var opEstado = estadoDAO.findById(pedido.getEstado().getNombre());
            if (opEstado.isEmpty()) return ResponseBuilder.Fail("No se ha encontrado el estado");
            pedido.setEstado(opEstado.get());
            if (user == null) return ResponseBuilder.Fail("No se ha encontrado el usuario por su cedula");
            user.getPedidos().add(pedido);
            rep.save(user);
            return new ResponseBuilder<Pedido>().withSuccess(true).withData(pedido);
        } catch (Exception e) {
            return ResponseBuilder.Error(e);
        }
    }
}

