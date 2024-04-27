package com.upc.cuptap_restapi.Events.Interfaces;

public interface DataBaseEventListener extends EventListener {
    default void handleCreate(Object transmitionData) {
        try {
            onCreateSendToChannel(transmitionData);
    } catch (Exception ignored) {
        try {
            defaultMessage(transmitionData);
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
            defaultMessage(transmitionData);
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
                defaultMessage(transmitionData);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    void onDeleteSendToChannel(Object transmitionData);
    void onSaveSendToChannel(Object transmitionData);
    void onCreateSendToChannel(Object transmitionData);
    default void defaultMessage(Object transmitionData){
        throw new RuntimeException("defaultMessage not implemented.");
    }
}
