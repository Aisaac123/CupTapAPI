package com.upc.cuptap_restapi.Controllers.Shared.Instances;

import com.upc.cuptap_restapi.Controllers.Shared.Implements.RController;
import com.upc.cuptap_restapi.Models.Interfaces.Entities.ReadEntity;

public interface RControllerInstance<E extends ReadEntity, ID extends Comparable<ID>> {
    RController<E, ID> Read();
}
