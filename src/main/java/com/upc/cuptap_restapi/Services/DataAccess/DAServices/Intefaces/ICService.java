package com.upc.cuptap_restapi.Services.DataAccess.DAServices.Intefaces;

import com.upc.cuptap_restapi.Models.Interfaces.Entities.CreateEntity;
import com.upc.cuptap_restapi.Models.Utilities.Response;
import com.upc.cuptap_restapi.Repositories.Repository.GlobalRepository;

import java.util.List;


/**
 *  Interface of Create Services and implement interfaces {@link Services}
 */
public interface ICService<E extends CreateEntity> extends Services {

    Response<E> Save(E entity);

    Response<List<E>> Save(Iterable<E> iterable);

}
