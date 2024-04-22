package com.upc.cuptap_restapi.Controllers.Providers.ProvidersInstances;

import com.upc.cuptap_restapi.Controllers.Providers.Providers.CController;
import com.upc.cuptap_restapi.Models.Interfaces.Entities.CreateEntity;

public interface CControllerInstance<E extends CreateEntity, ID extends Comparable<ID>> {
    CController<E, ID> Persist();
}
