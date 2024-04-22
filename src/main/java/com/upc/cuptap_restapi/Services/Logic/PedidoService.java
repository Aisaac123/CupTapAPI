package com.upc.cuptap_restapi.Services.Logic;

import com.upc.cuptap_restapi.Models.DTO.DTORequest.PedidoAndDetallesRequest;
import com.upc.cuptap_restapi.Models.DTO.DTORequest.PedidoRequest;
import com.upc.cuptap_restapi.Models.Entities.Combo;
import com.upc.cuptap_restapi.Models.Entities.DetallesPedido;
import com.upc.cuptap_restapi.Models.Entities.Pedido;
import com.upc.cuptap_restapi.Models.Entities.Producto;
import com.upc.cuptap_restapi.Repositories.DAO.ComboDAO;
import com.upc.cuptap_restapi.Repositories.DAO.PedidoDAO;
import com.upc.cuptap_restapi.Repositories.DAO.ProductoDAO;
import com.upc.cuptap_restapi.Services.Providers.ProvidersInstances.CRUDServiceInstance;
import com.upc.cuptap_restapi.Services.Providers.Providers.Implements.CService;
import com.upc.cuptap_restapi.Services.Providers.Providers.Implements.DService;
import com.upc.cuptap_restapi.Services.Providers.Providers.Implements.RService;
import com.upc.cuptap_restapi.Services.Providers.Providers.Implements.UService;
import com.upc.cuptap_restapi.Services.Utils.Options.Reconstruct;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class PedidoService implements CRUDServiceInstance<Pedido, Long>, Reconstruct<Pedido, PedidoRequest> {
    final
    PedidoDAO rep;
    final
    UsuarioService servUsuario;

    final
    EstadoService servEstado;
    private final ProductoDAO productoDAO;
    private final ComboDAO comboDAO;

    public PedidoService(PedidoDAO rep, UsuarioService servUsuario, EstadoService servEstado,
                         ProductoDAO productoDAO,
                         ComboDAO comboDAO) {
        this.rep = rep;
        this.servUsuario = servUsuario;
        this.servEstado = servEstado;
        this.productoDAO = productoDAO;
        this.comboDAO = comboDAO;
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

    @Override
    public Pedido Reconstruct(PedidoRequest pedidoDto) {

        Pedido pedido = pedidoDto.toEntity();
        pedido.setUsuario(servUsuario.GetByCedula(pedidoDto.usuarioCedula()).getData());
        pedido.setEstado(servEstado.Read().GetById("estado 1").getData());
        return pedido;
    }

    public Pedido Reconstruct(PedidoAndDetallesRequest pedidoDto) {
        Pedido pedido = pedidoDto.toEntity();
        pedido.setUsuario(servUsuario.GetByCedula(pedidoDto.usuarioCedula()).getData());
        pedido.setEstado(servEstado.Read().GetById("estado 1").getData());

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

        Map<String, Producto> productosMap = productoDAO.findByIds(productoNombres);
        Map<String, Combo> combosMap = comboDAO.findByIds(comboNombres);

        pedido.getDetalles().forEach(detalle -> {
            detalle.setPedido(pedido);
            if (detalle.getProducto() != null) {
                detalle.setProducto(productosMap.get(detalle.getProducto().getNombre()));
            }
            if (detalle.getCombo() != null) {
                detalle.setCombo(combosMap.get(detalle.getCombo().getNombre()));
            }
        });

        return pedido;
    }


}
