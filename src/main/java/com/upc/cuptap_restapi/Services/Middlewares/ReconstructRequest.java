package com.upc.cuptap_restapi.Services.Middlewares;

import com.upc.cuptap_restapi.Models.DTO.DTORequest.*;
import com.upc.cuptap_restapi.Models.Entities.*;
import com.upc.cuptap_restapi.Repositories.DAO.*;
import com.upc.cuptap_restapi.Services.Middlewares.Validations.Requests.PedidoRequestValidations;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ReconstructRequest {

    final UsuarioDAO usuarioDAO;
    private final ProductoDAO productoDAO;
    private final ComboDAO comboDAO;
    private final PromocionDAO promocionDAO;
    private final PedidoDAO pedidoDAO;
    private final DetallesPedidoDAO detallesPedidoDAO;

    public ReconstructRequest(UsuarioDAO usuarioDAO,
                              ProductoDAO productoDAO,
                              ComboDAO comboDAO,
                              PromocionDAO promocionDAO,
                              PedidoDAO pedidoDAO,
                              DetallesPedidoDAO detallesPedidoDAO) {
        this.usuarioDAO = usuarioDAO;
        this.productoDAO = productoDAO;
        this.comboDAO = comboDAO;
        this.promocionDAO = promocionDAO;
        this.pedidoDAO = pedidoDAO;
        this.detallesPedidoDAO = detallesPedidoDAO;
    }

    /**
     Reconstruct of {@link Pedido}
     */
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

            var res = PedidoRequestValidations.Validate(detalle);

            if (!res.isSuccess()) try {
                throw new Exception(res.getMsg());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            if (detalle.getProducto() != null && detalle.getProducto().getNombre() != null) {
                detalle.setCombo(null);
                if (!detallesPedidoDAO.HasStockForPedido(detalle.getCantidad())) try {
                    throw new Exception("No hay suficiente productos en stock de: " + detalle.getProducto().getNombre() + "para este pedido");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            else if (detalle.getCombo() != null && detalle.getCombo().getNombre() != null) {
                detalle.setPedido(null);
                if (!detallesPedidoDAO.HasStockForCombos(detalle.getCantidad())) try {
                    throw new Exception("No hay suficiente combos en stock de: " + detalle.getProducto().getNombre()+ "para este pedido");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            detalle.setPedido(pedido);
        });

        return pedido;
    }

    /**
     Reconstruct of {@link Combo}
     */
    public Combo reconstruct(ComboRequest requestDTO) {
        var combo = requestDTO.toEntity();
        if (combo.getPromocion().getId() != null)
            combo.setPromocion(promocionDAO.findById(combo.getPromocion().getId()).orElse(null));
        return combo;
    }

    /**
        Reconstruct of {@link Pago}
     */
    public Pago reconstruct(PagoRequest requestDTO) {
        var pago = requestDTO.toEntity();
        pago.setPedido(pedidoDAO.findById(pago.getPedido().getId()).orElse(null));

        return pago;
    }

    /**
     Reconstruct of {@link Productos_Combo}
     */
    public Productos_Combo reconstruct(Productos_ComboRequest requestDTO) {
        var p_combos = requestDTO.toEntity();

        p_combos.setCombo(comboDAO.findById(p_combos.getCombo().getNombre()).orElse(null));
        p_combos.setProducto(productoDAO.findById(p_combos.getProducto().getNombre()).orElse(null));

        return p_combos;
    }

    /**
     Reconstruct of {@link Producto}
     */
    public Producto reconstruct(ProductoRequest requestDTO) {
        var producto = requestDTO.toEntity();
        if (producto.getPromocion() != null && producto.getPromocion().getId() != null)
            producto.setPromocion(promocionDAO.findById(producto.getPromocion().getId()).orElse(null));
        return producto;
    }

    /**
     Reconstruct of {@link Promocion}
     */
    public Promocion reconstruct(PromocionRequest requestDTO) {
        return requestDTO.toEntity();
    }

    /**
     Reconstruct of {@link Usuario}
     */
    public Usuario reconstruct(UsuarioRequest requestDTO) {
        return requestDTO.toEntity();
    }

}
