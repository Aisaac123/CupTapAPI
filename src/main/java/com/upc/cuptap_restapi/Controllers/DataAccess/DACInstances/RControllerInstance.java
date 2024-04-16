package com.upc.cuptap_restapi.Controllers.DataAccess.DACInstances;

import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.RController;
import com.upc.cuptap_restapi.Models.Interfaces.Entities.ReadEntity;

public interface RControllerInstance<E extends ReadEntity, ID extends Comparable<ID>> {
    RController<E, ID> Read();
}
