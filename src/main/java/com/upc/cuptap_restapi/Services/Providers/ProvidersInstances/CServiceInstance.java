package com.upc.cuptap_restapi.Services.Providers.ProvidersInstances;

import com.upc.cuptap_restapi.Models.Interfaces.Entities.CreateEntity;
import com.upc.cuptap_restapi.Services.Providers.Providers.Implements.CService;

public interface CServiceInstance<E extends CreateEntity, ID extends Comparable<ID>> {
    CService<E, ID> Persist();
}
