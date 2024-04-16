package com.upc.cuptap_restapi.Services.DataAccess.DAServices.Intefaces;

import com.upc.cuptap_restapi.Models.Interfaces.Entities.UpdateEntity;
import com.upc.cuptap_restapi.Models.Utilities.Response;

import java.util.Map;


/**
 *  Interface of Updating Services and implement interfaces {@link Services}
 */
public interface IUService<E extends UpdateEntity, ID extends Comparable<ID>> extends Services {

    Response<Map<String, E>> Update(E entity, ID old_id);

}
