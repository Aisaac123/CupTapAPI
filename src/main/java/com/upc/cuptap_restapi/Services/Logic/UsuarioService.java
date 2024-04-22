package com.upc.cuptap_restapi.Services.Logic;

import com.upc.cuptap_restapi.Models.DTO.DTOLazyLoad.UsuarioLazy;
import com.upc.cuptap_restapi.Models.DTO.DTORequest.DetallesPedidoRequestNoId;
import com.upc.cuptap_restapi.Models.DTO.DTORequest.PedidoAndDetallesRequest;
import com.upc.cuptap_restapi.Models.DTO.DTORequest.UsuarioRequest;
import com.upc.cuptap_restapi.Models.Entities.*;
import com.upc.cuptap_restapi.Models.Utils.Response;
import com.upc.cuptap_restapi.Models.Utils.ResponseBuilder;
import com.upc.cuptap_restapi.Repositories.DAO.EstadoDAO;
import com.upc.cuptap_restapi.Repositories.DAO.PedidoDAO;
import com.upc.cuptap_restapi.Repositories.DAO.ProductoDAO;
import com.upc.cuptap_restapi.Repositories.DAO.UsuarioDAO;
import com.upc.cuptap_restapi.Services.Providers.ProvidersInstances.CRUDServiceInstance;
import com.upc.cuptap_restapi.Services.Providers.Providers.Implements.CService;
import com.upc.cuptap_restapi.Services.Providers.Providers.Implements.DService;
import com.upc.cuptap_restapi.Services.Providers.Providers.Implements.RService;
import com.upc.cuptap_restapi.Services.Providers.Providers.Implements.UService;
import com.upc.cuptap_restapi.Services.Utils.Options.ParamOptions;
import com.upc.cuptap_restapi.Services.Utils.Options.ReadingOptions;
import com.upc.cuptap_restapi.Services.Utils.Options.Reconstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements CRUDServiceInstance<Usuario, UUID>, Reconstruct<Usuario, UsuarioRequest> {

    private final UsuarioDAO rep;
    private final EstadoDAO estadoDAO;
    final
    PedidoService pedidoService;
    final
    PedidoDAO pedidoDAO;
    private final ProductoDAO productoDAO;

    public UsuarioService(UsuarioDAO repository,
                          EstadoDAO estadoDAO, PedidoDAO pedidoDAO,
                          ProductoDAO productoDAO, PedidoService pedidoService) {
        rep = repository;
        this.estadoDAO = estadoDAO;
        this.pedidoDAO = pedidoDAO;
        this.productoDAO = productoDAO;
        this.pedidoService = pedidoService;
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

    public Response<Pedido> AddPedidoToUsuario(PedidoAndDetallesRequest pedidoDTO) {
        try {
            Usuario user = rep.findByCedula(pedidoDTO.usuarioCedula());
            if (user == null) return ResponseBuilder.Fail("No se ha encontrado el usuario por su cedula");

            var pedido = pedidoService.Reconstruct(pedidoDTO);
            user.addPedido(pedido);
            rep.save(user);

            return new ResponseBuilder<Pedido>().withSuccess(true).withData(pedido);
        } catch (Exception e) {
            return ResponseBuilder.Error(e);
        }
    }


    @Override
    public Usuario Reconstruct(UsuarioRequest requestDTO) {
        return requestDTO.toEntity();
    }
}

