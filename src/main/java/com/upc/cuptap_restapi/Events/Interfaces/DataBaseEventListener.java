package com.upc.cuptap_restapi.Events.Interfaces;

public interface DataBaseEventListener extends EventListener {
    default void handleCreate(Object transmitionData) {
        try {
            onCreateSendToChannel(transmitionData);
    } catch (Exception ignored) {
        try {
            sendToAllChannels(transmitionData);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

    default void handleSave(Object transmitionData) {
        try {
            onSaveSendToChannel(transmitionData);
    } catch (Exception ignored) {
        try {
            sendToAllChannels(transmitionData);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

    default void handleDelete(Object transmitionData) {
        try {
            onDeleteSendToChannel(transmitionData);
        } catch (Exception ignored) {
            try {
                sendToAllChannels(transmitionData);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    default void onDeleteSendToChannel(Object transmitionData){
        throw new RuntimeException("onDeleteSendToChannel not implemented.");
    }
    default void onSaveSendToChannel(Object transmitionData){
        throw new RuntimeException("onSaveSendToChannel not implemented.");
    }
    default void onCreateSendToChannel(Object transmitionData){
        throw new RuntimeException("onCreateSendToChannel not implemented.");
    }
    default void sendToAllChannels(Object transmitionData){
        throw new RuntimeException("sendToAllChannels not implemented.");
    }
}
