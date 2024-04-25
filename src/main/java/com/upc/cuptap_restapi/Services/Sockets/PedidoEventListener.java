package com.upc.cuptap_restapi.Services.Sockets;

import com.upc.cuptap_restapi.Models.Entities.Pedido;
import com.upc.cuptap_restapi.Repositories.DAO.PedidoDAO;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketHandler;

import java.util.List;

@Service
public class PedidoEventListener {
    public PedidoEventListener(SimpMessagingTemplate template,
                               PedidoDAO pedidoDAO) {

        this.template = template;
        this.pedidoDAO = pedidoDAO;
    }
    private final SimpMessagingTemplate template;
    private final PedidoDAO pedidoDAO;

    @HandleAfterCreate
    public void handlePedidoCreate() {
        sendUpdatedPedidos();
    }

    @HandleAfterSave
    public void handlePedidoSave() {
        sendUpdatedPedidos();
    }

    @HandleAfterDelete
    public void handlePedidoDelete() {
        sendUpdatedPedidos();
    }

    private void sendUpdatedPedidos() {
        List<Pedido> pedidos = pedidoDAO.findAll();
        this.template.convertAndSend("/topic/pedidos", pedidos);
    }
}