package com.upc.cuptap_restapi.Controllers.Providers.ProvidersInstances;

import com.upc.cuptap_restapi.Controllers.Providers.Providers.UController;
import com.upc.cuptap_restapi.Models.Interfaces.Entities.UpdateEntity;

public interface UControllerInstance<E extends UpdateEntity, ID extends Comparable<ID>> {
    UController<E, ID> Modify();

}
