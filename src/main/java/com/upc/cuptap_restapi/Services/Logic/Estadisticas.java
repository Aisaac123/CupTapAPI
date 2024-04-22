package com.upc.cuptap_restapi.Services.Logic;

import com.upc.cuptap_restapi.Repositories.DAO.ComboDAO;
import com.upc.cuptap_restapi.Repositories.DAO.PedidoDAO;
import com.upc.cuptap_restapi.Repositories.DAO.ProductoDAO;
import com.upc.cuptap_restapi.Repositories.DAO.UsuarioDAO;
import org.springframework.stereotype.Service;


@Service
public class Estadisticas {

    private final PedidoDAO pedidoDAO;
    private final UsuarioDAO usuarioDAO;
    private final ProductoDAO productoDAO;
    private final ComboDAO comboDAO;

    private Estadisticas(PedidoDAO pedidoDAO,
                         UsuarioDAO usuarioDAO,
                         ProductoDAO productoDAO,
                         ComboDAO comboDAO) {
        this.pedidoDAO = pedidoDAO;
        this.usuarioDAO = usuarioDAO;
        this.productoDAO = productoDAO;
        this.comboDAO = comboDAO;
    }

    public double totalRecaudado() {
        return pedidoDAO.sumPrecio();
    }

    public long cantPedidos() {
        return pedidoDAO.count();
    }

    public long cantUsuarios() {
        return usuarioDAO.count();
    }

    public long cantProductos() {
        return productoDAO.count();
    }

    public long cantCombos() {
        return comboDAO.count();
    }

}
