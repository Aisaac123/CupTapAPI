package com.upc.cuptap_restapi.Events.Event;

import com.upc.cuptap_restapi.Events.Interfaces.DataBaseEventListener;
import com.upc.cuptap_restapi.Events.Utils.WsChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProductoEventListener implements DataBaseEventListener {

    final
    SimpMessagingTemplate template;

    public ProductoEventListener(SimpMessagingTemplate template) {
        this.template = template;
    }

    @Override
    public void onDeleteSendToChannel(Object transmitionData) {
        template.setDefaultDestination(WsChannel.TOPIC.setRoute("productos/deleted"));
        template.convertAndSend(transmitionData);
    }

    @Override
    public void onSaveSendToChannel(Object transmitionData) {
        template.setDefaultDestination(WsChannel.TOPIC.setRoute("productos/saved"));
        template.convertAndSend(transmitionData);
    }

    @Override
    public void onCreateSendToChannel(Object transmitionData) {
        template.setDefaultDestination(WsChannel.TOPIC.setRoute("productos/created"));
        template.convertAndSend(transmitionData);
    }

}
