package com.upc.cuptap_restapi.Services.Providers.Providers.Interfaces;

import com.upc.cuptap_restapi.Models.Interfaces.Entities.CreateEntity;
import com.upc.cuptap_restapi.Models.Utils.Response;

import java.util.List;


/**
 * Interface of Create Services and implement interfaces {@link Services}
 */
public interface ICService<E extends CreateEntity> extends Services {

    Response<E> Save(E entity);

    Response<List<E>> Save(Iterable<E> iterable);

}
