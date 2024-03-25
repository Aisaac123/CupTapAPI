package com.upc.cuptap_restapi.Controllers.DataAccess.DACInstances;

import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.CController;
import com.upc.cuptap_restapi.Models.Interfaces.CreateEntity;

public interface CControllerInstance<E extends CreateEntity, ID extends Comparable<ID>> {
    CController<E, ID> Persist();
}
