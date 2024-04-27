package com.upc.cuptap_restapi.Services.Shared.Interfaces;

import com.upc.cuptap_restapi.Models.Interfaces.Entities.UpdateEntity;
import com.upc.cuptap_restapi.Models.Utils.Response;

import java.util.Map;


/**
 * Interface of Updating Services and implement interfaces {@link Services}
 */
public interface IUService<E extends UpdateEntity, ID extends Comparable<ID>> extends Services {

    default Response<Map<String, E>> Update(E entity, ID old_id){
        return Update(entity, old_id, false);
    }

    Response<Map<String, E>> Update(E entity, ID old_id, boolean transmit);


}
