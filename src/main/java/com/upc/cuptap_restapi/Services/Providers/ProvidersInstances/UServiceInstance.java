package com.upc.cuptap_restapi.Services.Providers.ProvidersInstances;

import com.upc.cuptap_restapi.Models.Interfaces.Entities.UpdateEntity;
import com.upc.cuptap_restapi.Services.Providers.Providers.Implements.UService;

public interface UServiceInstance<E extends UpdateEntity, ID extends Comparable<ID>> {
    UService<E, ID> Modify();


}
