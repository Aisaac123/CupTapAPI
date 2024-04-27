package com.upc.cuptap_restapi.Events.Event;


import com.upc.cuptap_restapi.Events.Utils.WsChannel;
import com.upc.cuptap_restapi.Events.Interfaces.DataBaseEventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class PedidoEventListener implements DataBaseEventListener {
    public PedidoEventListener(SimpMessagingTemplate template){

        this.template = template;
    }
    private final SimpMessagingTemplate template;

    @Override
    public void onSaveSendToChannel(Object transmitionData) {
        this.template.setDefaultDestination(WsChannel.TOPIC.setRoute("pedidos/saved"));
        this.template.convertAndSend(transmitionData);
    }

    @Override
    public void onDeleteSendToChannel(Object transmitionData) {
        this.template.setDefaultDestination(WsChannel.TOPIC.setRoute("pedidos/deleted"));
        this.template.convertAndSend(transmitionData);
    }

    @Override
    public void onCreateSendToChannel(Object transmitionData) {
        this.template.setDefaultDestination(WsChannel.TOPIC.setRoute("pedidos/created"));
        this.template.convertAndSend(transmitionData);
    }
}