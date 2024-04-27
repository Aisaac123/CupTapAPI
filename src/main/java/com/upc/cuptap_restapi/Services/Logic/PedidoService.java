package com.upc.cuptap_restapi.Services.Logic;

import com.upc.cuptap_restapi.Models.DTO.DTORequest.PedidoRequest;
import com.upc.cuptap_restapi.Models.DTO.DTORequest.PedidoRequestNoCedula;
import com.upc.cuptap_restapi.Models.Entities.Pedido;
import com.upc.cuptap_restapi.Models.Entities.Usuario;
import com.upc.cuptap_restapi.Models.Utils.Response;
import com.upc.cuptap_restapi.Models.Utils.ResponseBuilder;
import com.upc.cuptap_restapi.Repositories.DAO.PedidoDAO;
import com.upc.cuptap_restapi.Repositories.DAO.ProductoDAO;
import com.upc.cuptap_restapi.Repositories.DAO.PromocionDAO;
import com.upc.cuptap_restapi.Repositories.DAO.UsuarioDAO;
import com.upc.cuptap_restapi.Services.Validators.DetallePedidoValidator;
import com.upc.cuptap_restapi.Services.Shared.Implements.CService;
import com.upc.cuptap_restapi.Services.Shared.Implements.DService;
import com.upc.cuptap_restapi.Services.Shared.Implements.RService;
import com.upc.cuptap_restapi.Services.Shared.Implements.UService;
import com.upc.cuptap_restapi.Services.Shared.Instances.CRUDServiceInstance;
import com.upc.cuptap_restapi.Events.Event.PedidoEventListener;
import org.springframework.stereotype.Service;

@Service
public class PedidoService implements CRUDServiceInstance<Pedido, Long> {
    private final
    PedidoDAO rep;
    private final UsuarioDAO usuarioDAO;
    private final DetallePedidoValidator pedidoRequestValidations;
    final
    PedidoEventListener listener;
    private final ProductoDAO productoDAO;
    private final PromocionDAO promocionDAO;

    public PedidoService(PedidoDAO rep, UsuarioDAO usuarioDAO, DetallePedidoValidator pedidoRequestValidations, PedidoEventListener listener,
                         ProductoDAO productoDAO,
                         PromocionDAO promocionDAO) {
        this.rep = rep;
        this.usuarioDAO = usuarioDAO;
        this.pedidoRequestValidations = pedidoRequestValidations;
        this.listener = listener;
        this.productoDAO = productoDAO;
        this.promocionDAO = promocionDAO;
    }

    @Override
    public CService<Pedido, Long> Persist() {
        return new CService<>(rep, listener);
    }

    @Override
    public DService<Pedido, Long> Remove() {
        return new DService<>(rep, listener);
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

        return calcularTotal(pedido);
    }

    private Pedido calcularTotal(Pedido pedido){
        var total = 0.0;
        for (var item: pedido.getDetalles()) {

            var res = pedidoRequestValidations.Validate(item);

            if (!res.isSuccess()) try {
                throw new Exception(res.getMsg());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            item.setPedido(pedido);

            var producto = productoDAO.findById(item.getProducto().getNombre()).orElse(null);
            item.setProducto(producto);

            double descuento = 0.0;
            double valordescontado = 0.0;
            assert producto != null;
            if (producto.getPromocion() != null){
                descuento =  Double.parseDouble( String.valueOf( producto.getPromocion().getDescuento())) / 100;
                descuento = Math.round(descuento * 100) / 100.00;
                valordescontado = (producto.getPrecio() * descuento);
                item.setValor_descontado(valordescontado * item.getCantidad());
                item.setPromocion_aplicada(producto.getPromocion().getNombre());
            }
            var subtotal = (producto.getPrecio() - valordescontado) * item.getCantidad();
            item.setSubtotal(subtotal);
            total += subtotal;
        }
        pedido.setTotal(total);
        return pedido;
    }
    public Response chageEstado(String estado, Long id){
        try {
            rep.changeEstado(estado, id);
            listener.onSaveSendToChannel(rep.findLast());
            return ResponseBuilder.Success("Se actualizado el estado del pedido a: " + estado);
        } catch (Exception e) {
            return ResponseBuilder.Error(e);
        }
    }

}
