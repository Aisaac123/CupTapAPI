package com.upc.cuptap_restapi.Services.Shared.Instances;

import com.upc.cuptap_restapi.Models.Interfaces.Entities.ReadEntity;
import com.upc.cuptap_restapi.Services.Shared.Implements.RService;

public interface RServiceInstance<E extends ReadEntity, ID extends Comparable<ID>> {
    RService<E, ID> Read();
}
