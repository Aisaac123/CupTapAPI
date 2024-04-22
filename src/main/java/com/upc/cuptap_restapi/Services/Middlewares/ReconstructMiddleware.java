package com.upc.cuptap_restapi.Services.Middlewares;

import com.upc.cuptap_restapi.Models.DTO.DTORequest.*;
import com.upc.cuptap_restapi.Models.Entities.*;
import com.upc.cuptap_restapi.Repositories.DAO.*;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ReconstructMiddleware {

    final UsuarioDAO usuarioDAO;
    private final EstadoDAO estadoDAO;
    private final ProductoDAO productoDAO;
    private final ComboDAO comboDAO;
    private final PromocionDAO promocionDAO;
    private final PedidoDAO pedidoDAO;

    public ReconstructMiddleware(UsuarioDAO usuarioDAO,
                                 EstadoDAO estadoDAO,
                                 ProductoDAO productoDAO,
                                 ComboDAO comboDAO,
                                 PromocionDAO promocionDAO,
                                 PedidoDAO pedidoDAO) {
        this.usuarioDAO = usuarioDAO;
        this.estadoDAO = estadoDAO;
        this.productoDAO = productoDAO;
        this.comboDAO = comboDAO;
        this.promocionDAO = promocionDAO;
        this.pedidoDAO = pedidoDAO;
    }

    // Pedidos
    public Pedido reconstruct(PedidoAndDetallesRequest pedidoDto) {
        return reconstruct(pedidoDto.toEntity());
    }

    public Pedido reconstruct(PedidoAndDetallesRequest pedidoDto, Usuario user) {
        var pedido = pedidoDto.toEntity();
        pedido.setUsuario(user);
        return reconstruct(pedido);
    }

    public Pedido reconstruct(Pedido pedido) {
        if (pedido.getUsuario().getNombre() == null)
            pedido.setUsuario(usuarioDAO.findByCedula(pedido.getUsuario().getCedula()));

        pedido.setEstado(estadoDAO.findById(pedido.getEstado().getNombre()).orElse(null));

        Set<String> productoNombres = new HashSet<>();
        Set<String> comboNombres = new HashSet<>();

        for (DetallesPedido detallePedido : pedido.getDetalles()) {
            if (detallePedido.getProducto() != null) {
                productoNombres.add(detallePedido.getProducto().getNombre());
            }
            if (detallePedido.getCombo() != null) {
                comboNombres.add(detallePedido.getCombo().getNombre());
            }
        }

        List<Producto> productosMap = productoDAO.findByIds(productoNombres);
        List<Combo> combosMap = comboDAO.findByIds(comboNombres);

        AtomicInteger i = new AtomicInteger();
        pedido.getDetalles().forEach(detalle -> {
            if (detalle.getProducto() != null && detalle.getProducto().getNombre() != null) {
                detalle.setCombo(null);
                detalle.setProducto(productosMap.get(i.get()));
            }
            if (detalle.getCombo() != null && detalle.getCombo().getNombre() != null) {
                detalle.setPedido(null);
                detalle.setCombo(combosMap.get(i.get()));
            }
            detalle.setPedido(pedido);
            i.getAndIncrement();
        });

        return pedido;
    }

    public Combo reconstruct(ComboRequest requestDTO) {
        var combo = requestDTO.toEntity();
        if (combo.getPromocion().getId() != null)
            combo.setPromocion(promocionDAO.findById(combo.getPromocion().getId()).orElse(null));
        return combo;
    }

    public DetallesPedido reconstruct(DetallesPedidoRequest requestDTO) {
        var detalles = requestDTO.toEntity();
        detalles.setPedido(pedidoDAO.findById(detalles.getPedido().getId()).orElse(null));
        detalles.setCombo(comboDAO.findById(detalles.getCombo().getNombre()).orElse(null));
        detalles.setProducto(productoDAO.findById(detalles.getProducto().getNombre()).orElse(null));
        return detalles;
    }

    public Pago reconstruct(PagoRequest requestDTO) {
        var pago = requestDTO.toEntity();
        pago.setPedido(pedidoDAO.findById(pago.getPedido().getId()).orElse(null));

        return pago;
    }

    public Pedido reconstruct(PedidoRequest pedidoDto) {

        Pedido pedido = pedidoDto.toEntity();
        pedido.setUsuario(usuarioDAO.findByCedula(pedidoDto.usuarioCedula()));
        pedido.setEstado(estadoDAO.findById(pedidoDto.estadoNombre()).orElse(null));
        return pedido;
    }

    public Productos_Combo reconstruct(Productos_ComboRequest requestDTO) {
        var p_combos = requestDTO.toEntity();

        p_combos.setCombo(comboDAO.findById(p_combos.getCombo().getNombre()).orElse(null));
        p_combos.setProducto(productoDAO.findById(p_combos.getProducto().getNombre()).orElse(null));

        return p_combos;
    }

    public Producto reconstruct(ProductoRequest requestDTO) {
        var producto = requestDTO.toEntity();
        if (producto.getPromocion() != null && producto.getPromocion().getId() != null)
            producto.setPromocion(promocionDAO.findById(producto.getPromocion().getId()).orElse(null));
        return producto;
    }

    public Promocion reconstruct(PromocionRequest requestDTO) {
        return requestDTO.toEntity();
    }

    public Usuario reconstruct(UsuarioRequest requestDTO) {
        return requestDTO.toEntity();
    }

}
