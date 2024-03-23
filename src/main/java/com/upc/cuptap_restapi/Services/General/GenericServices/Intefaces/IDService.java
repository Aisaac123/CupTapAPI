package com.upc.cuptap_restapi.Services.General.GenericServices.Intefaces;

import com.upc.cuptap_restapi.Models.Interface.DeleteEnity;
import com.upc.cuptap_restapi.Models.Utilities.Response;

import java.util.List;

public interface IDService<E extends DeleteEnity, ID extends Comparable<ID>> extends Services {
    Response<E> Delete(ID id);

    Response<E> Delete(E entity);

    Response<List<E>> Delete(Iterable<ID> iterable);

    Response<List<E>> DeleteAll();
}
