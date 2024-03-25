package com.upc.cuptap_restapi.Controllers.DataAccess.DACInstances;

import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.UController;
import com.upc.cuptap_restapi.Models.Interfaces.UpdateEntity;

public interface UControllerInstance<E extends UpdateEntity, ID extends Comparable<ID>> {
    UController<E, ID> Modify();

}
