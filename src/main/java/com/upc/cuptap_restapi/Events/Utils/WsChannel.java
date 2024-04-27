package com.upc.cuptap_restapi.Events.Utils;

public enum WsChannel {

    TOPIC("/topic/");

    WsChannel(String texto) {
        this.channel = texto;
    }
    private final String channel;


    public String setRoute(String model) {
        return channel + model;
    }
}
