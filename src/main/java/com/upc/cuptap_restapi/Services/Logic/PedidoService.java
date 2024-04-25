package com.upc.cuptap_restapi.Services.Logic;

import com.upc.cuptap_restapi.Models.DTO.DTORequest.PedidoRequest;
import com.upc.cuptap_restapi.Models.DTO.DTORequest.PedidoRequestNoCedula;
import com.upc.cuptap_restapi.Models.Entities.Pedido;
import com.upc.cuptap_restapi.Models.Entities.Usuario;
import com.upc.cuptap_restapi.Models.Utils.Response;
import com.upc.cuptap_restapi.Repositories.DAO.PedidoDAO;
import com.upc.cuptap_restapi.Repositories.DAO.UsuarioDAO;
import com.upc.cuptap_restapi.Services.Middlewares.Validations.Requests.PedidoRequestValidations;
import com.upc.cuptap_restapi.Services.Providers.Providers.Implements.CService;
import com.upc.cuptap_restapi.Services.Providers.Providers.Implements.DService;
import com.upc.cuptap_restapi.Services.Providers.Providers.Implements.RService;
import com.upc.cuptap_restapi.Services.Providers.Providers.Implements.UService;
import com.upc.cuptap_restapi.Services.Providers.ProvidersInstances.CRUDServiceInstance;
import com.upc.cuptap_restapi.Services.Sockets.PedidoEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService implements CRUDServiceInstance<Pedido, Long> {
    private final
    PedidoDAO rep;
    private final UsuarioDAO usuarioDAO;
    private final PedidoRequestValidations pedidoRequestValidations;
    final
    PedidoEventListener listener;

    public PedidoService(PedidoDAO rep, UsuarioDAO usuarioDAO, PedidoRequestValidations pedidoRequestValidations, PedidoEventListener listener) {
        this.rep = rep;
        this.usuarioDAO = usuarioDAO;
        this.pedidoRequestValidations = pedidoRequestValidations;
        this.listener = listener;
    }

    @Override
    public CService<Pedido, Long> Persist() {
        return new CService<>(rep);
    }

    @Override
    public DService<Pedido, Long> Remove() {
        return new DService<>(rep);
    }

    @Override
    public RService<Pedido, Long> Read() {
        return new RService<>(rep);
    }

    @Override
    public UService<Pedido, Long> Modify() {
        return new UService<>(rep);
    }

    public Pedido reconstruct(PedidoRequestNoCedula pedidoDto, Usuario user) {
        var pedido = pedidoDto.toEntity();
        pedido.setUsuario(user);
        return reconstruct(pedido);
    }
    public Pedido reconstruct(PedidoRequest pedidoDto, Usuario user) {
        var pedido = pedidoDto.toEntity();
        pedido.setUsuario(user);
        return reconstruct(pedido);
    }
    public Pedido reconstruct(Pedido pedido) {
        if (pedido.getUsuario().getNombre() == null)
            pedido.setUsuario(usuarioDAO.findByCedula(pedido.getUsuario().getCedula()));


        pedido.getDetalles().forEach(detalle -> {

            var res = pedidoRequestValidations.Validate(detalle);

            if (!res.isSuccess()) try {
                throw new Exception(res.getMsg());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            detalle.setPedido(pedido);
        });

        return pedido;
    }

    public Response<Pedido> Save(Pedido pedido){
        var res = Persist().Save(pedido);
        listener.handlePedidoSave();
        listener.handlePedidoCreate();
        listener.handlePedidoDelete();
        return res;
    }

}
