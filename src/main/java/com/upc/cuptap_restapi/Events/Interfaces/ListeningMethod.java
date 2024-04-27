package com.upc.cuptap_restapi.Events.Interfaces;

import com.upc.cuptap_restapi.Models.Interfaces.Entities.Entity;

import java.util.Set;

public interface ListeningMethod<E extends Entity> {
    public Set<E> listening();
}
