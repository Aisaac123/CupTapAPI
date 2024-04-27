package com.upc.cuptap_restapi.Services.Shared.Interfaces;

import com.upc.cuptap_restapi.Models.Interfaces.Entities.DeleteEnity;
import com.upc.cuptap_restapi.Models.Utils.Response;

import java.util.List;


/**
 * Interface of Deleting Services and implement interfaces {@link Services}
 */
public interface IDService<E extends DeleteEnity, ID extends Comparable<ID>> extends Services {
    default Response<E> Delete(ID id){
        return Delete(id, false);
    }

    default Response<E> Delete(E entity){
        return Delete(entity, false);
    }

    default Response<List<E>> Delete(Iterable<ID> iterable){
        return Delete(iterable, false);
    }

    default Response<List<E>> DeleteAll(){
        return DeleteAll(false);
    }

    Response<E> Delete(ID id, boolean transmit);

    Response<E> Delete(E entity, boolean transmit);

    Response<List<E>> Delete(Iterable<ID> iterable, boolean transmit);

    Response<List<E>> DeleteAll(boolean transmit);
}
