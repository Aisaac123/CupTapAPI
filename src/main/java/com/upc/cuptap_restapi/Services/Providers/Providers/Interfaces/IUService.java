package com.upc.cuptap_restapi.Services.Providers.Providers.Interfaces;

import com.upc.cuptap_restapi.Models.Interfaces.Entities.UpdateEntity;
import com.upc.cuptap_restapi.Models.Utils.Response;

import java.util.Map;


/**
 * Interface of Updating Services and implement interfaces {@link Services}
 */
public interface IUService<E extends UpdateEntity, ID extends Comparable<ID>> extends Services {

    Response<Map<String, E>> Update(E entity, ID old_id);

}
