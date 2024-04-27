package com.upc.cuptap_restapi.Services.Shared.Instances;

import com.upc.cuptap_restapi.Models.Interfaces.Entities.CreateEntity;
import com.upc.cuptap_restapi.Services.Shared.Implements.CService;

public interface CServiceInstance<E extends CreateEntity, ID extends Comparable<ID>> {
    CService<E, ID> Persist();
}
