package com.upc.cuptap_restapi.Services.Logic;

import com.upc.cuptap_restapi.Models.DTO.DTOLazyLoad.UsuarioLazy;
import com.upc.cuptap_restapi.Models.DTO.DTORequest.PedidoRequest;
import com.upc.cuptap_restapi.Models.DTO.DTORequest.PedidoRequestNoCedula;
import com.upc.cuptap_restapi.Models.DTO.DTORequest.UsuarioRequest;
import com.upc.cuptap_restapi.Models.Entities.Pedido;
import com.upc.cuptap_restapi.Models.Entities.Usuario;
import com.upc.cuptap_restapi.Models.Utils.Response;
import com.upc.cuptap_restapi.Models.Utils.ResponseBuilder;
import com.upc.cuptap_restapi.Repositories.DAO.UsuarioDAO;
import com.upc.cuptap_restapi.Services.Shared.Implements.CService;
import com.upc.cuptap_restapi.Services.Shared.Implements.DService;
import com.upc.cuptap_restapi.Services.Shared.Implements.RService;
import com.upc.cuptap_restapi.Services.Shared.Implements.UService;
import com.upc.cuptap_restapi.Services.Shared.Instances.CRUDServiceInstance;
import com.upc.cuptap_restapi.Services.Utils.Options.ParamOptions;
import com.upc.cuptap_restapi.Services.Utils.Options.ReadingOptions;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsuarioService implements CRUDServiceInstance<Usuario, UUID> {
    final
    PedidoService pedidoDAO;
    private final UsuarioDAO rep;

    public UsuarioService(UsuarioDAO repository, PedidoService pedidoDAO) {
        rep = repository;
        this.pedidoDAO = pedidoDAO;
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

    public Response<Map<String, Usuario>> UpdateByCedula(Usuario entity, String cedula) {
        try {
            Usuario user = rep.findByCedula(cedula);
            if (user != null) {
                Usuario oldEnt = (Usuario) user.clone();
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

    public Response GetByCedula(String cedula, ParamOptions options) {
        try {
            var readOption = (ReadingOptions) options;
            Usuario user = rep.findByCedula(cedula);
            if (user == null) {
                return ResponseBuilder.Fail("No se ha encontrado el usuario por su cedula");
            }
            if (readOption.isLazy()) {
                UsuarioLazy lazy = user.toLazy();
                return new ResponseBuilder<UsuarioLazy>().withSuccess(true).withData(lazy);
            }
            return new ResponseBuilder<Usuario>().withSuccess(true).withData(user);
        } catch (Exception e) {
            return ResponseBuilder.Error(e);
        }
    }

    public Response<Usuario> GetByCedula(String cedula) {
        try {
            Usuario user = rep.findByCedula(cedula);
            if (user == null) {
                return ResponseBuilder.Fail("No se ha encontrado el usuario por su cedula");
            }
            return new ResponseBuilder<Usuario>().withSuccess(true).withData(user);
        } catch (Exception e) {
            return ResponseBuilder.Error(e);
        }
    }

    public Response<List<Pedido>> GetPedidos(String cedula) {
        try {
            Usuario user = rep.findByCedula(cedula);
            if (user == null) {
                return ResponseBuilder.Fail("No se ha encontrado el usuario por su cedula");
            }
            if (user.getPedidos() == null || user.getPedidos().isEmpty())
                return new ResponseBuilder<List<Pedido>>().withSuccess(true).withMsg("Lista de pedidos vacia").withData(new ArrayList<>());
            return new ResponseBuilder<List<Pedido>>().withSuccess(true).withData(user.getPedidos());
        } catch (Exception e) {
            return ResponseBuilder.Error(e);
        }
    }

    public Response<Pedido> AddPedidoToUsuario(PedidoRequest pedidoDTO) {
        try {
            Usuario user = rep.findByCedula(pedidoDTO.usuario().cedula());
            if (user == null) return ResponseBuilder.Fail("No se ha encontrado el usuario por su cedula");
            var pedido = pedidoDAO.reconstruct(pedidoDTO, user);
            user.addPedido(pedido);
            rep.save(user);

            return new ResponseBuilder<Pedido>().withSuccess(true).withData(pedido);
        } catch (Exception e) {
            return ResponseBuilder.Error(e);
        }
    }

    public Response<Pedido> AddPedidoToUsuario(PedidoRequestNoCedula pedidoDTO, String cedula) {
        try {
            Usuario user = rep.findByCedula(cedula);
            if (user == null) return ResponseBuilder.Fail("No se ha encontrado el usuario por su cedula");
            var pedido = pedidoDAO.reconstruct(pedidoDTO, user);
            user.addPedido(pedido);
            rep.save(user);

            return ResponseBuilder.Success("Se ha registrado con exito el pedido del usuario: " + user.getCedula());
        } catch (Exception e) {
            return ResponseBuilder.Error(e);
        }
    }

    /**
     Reconstruct of {@link Usuario}
     */
    public Usuario reconstruct(UsuarioRequest requestDTO) {
        return requestDTO.toEntity();
    }
    public boolean AuthUser(String username, String password) {
        try {
            return rep.existsByUsernameAndPassword(username, password);
        } catch (Exception e) {
            return false;
        }
    }

}

