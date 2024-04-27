package com.upc.cuptap_restapi.Controllers.Shared.Instances;

import com.upc.cuptap_restapi.Controllers.Shared.Implements.UController;
import com.upc.cuptap_restapi.Models.Interfaces.Entities.UpdateEntity;

public interface UControllerInstance<E extends UpdateEntity, ID extends Comparable<ID>> {
    UController<E, ID> Modify();

}
