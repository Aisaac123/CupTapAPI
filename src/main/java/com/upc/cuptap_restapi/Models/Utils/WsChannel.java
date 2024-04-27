package com.upc.cuptap_restapi.Models.Utils;

public enum WsChannel {

    TOPIC("/topic/");

    WsChannel(String texto) {
        this.channel = texto;
    }
    private final String channel;


    public String setModel(String model) {
        return channel + model;
    }
}
