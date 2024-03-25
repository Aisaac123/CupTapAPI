package com.upc.cuptap_restapi.Services.DataAccess.DAServices.Intefaces;

import com.upc.cuptap_restapi.Models.Interfaces.CreateEntity;
import com.upc.cuptap_restapi.Models.Utilities.Response;

import java.util.List;

public interface ICService<E extends CreateEntity> extends Services {

    Response<E> Save(E entity);

    Response<List<E>> Save(Iterable<E> iterable);

}
