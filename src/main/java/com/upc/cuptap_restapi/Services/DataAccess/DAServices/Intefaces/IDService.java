package com.upc.cuptap_restapi.Services.DataAccess.DAServices.Intefaces;

import com.upc.cuptap_restapi.Models.Interfaces.Entities.DeleteEnity;
import com.upc.cuptap_restapi.Models.Utilities.Response;

import java.util.List;


/**
 *  Interface of Deleting Services and implement interfaces {@link Services}
 */
public interface IDService<E extends DeleteEnity, ID extends Comparable<ID>> extends Services {
    Response<E> Delete(ID id);

    Response<E> Delete(E entity);

    Response<List<E>> Delete(Iterable<ID> iterable);

    Response<List<E>> DeleteAll();
}
