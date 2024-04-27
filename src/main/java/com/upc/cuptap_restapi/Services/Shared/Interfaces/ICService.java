package com.upc.cuptap_restapi.Services.Shared.Interfaces;

import com.upc.cuptap_restapi.Models.Interfaces.Entities.CreateEntity;
import com.upc.cuptap_restapi.Models.Utils.Response;

import java.util.List;


/**
 * Interface of Create Services and implement interfaces {@link Services}
 */
public interface ICService<E extends CreateEntity> extends Services {

    default Response<E> Save(E entity){
        return Save(entity, false);
    }

    default Response<List<E>> Save(Iterable<E> iterable){
        return Save(iterable, false);
    }

    Response<E> Save(E entity, boolean transmit);

    Response<List<E>> Save(Iterable<E> iterable, boolean transmit);

}
