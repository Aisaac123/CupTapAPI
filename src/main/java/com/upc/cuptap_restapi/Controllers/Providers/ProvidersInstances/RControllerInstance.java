package com.upc.cuptap_restapi.Controllers.Providers.ProvidersInstances;

import com.upc.cuptap_restapi.Controllers.Providers.Providers.RController;
import com.upc.cuptap_restapi.Models.Interfaces.Entities.ReadEntity;

public interface RControllerInstance<E extends ReadEntity, ID extends Comparable<ID>> {
    RController<E, ID> Read();
}
