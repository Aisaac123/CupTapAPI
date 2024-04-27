package com.upc.cuptap_restapi.Controllers.Shared.Instances;

import com.upc.cuptap_restapi.Controllers.Shared.Implements.DController;
import com.upc.cuptap_restapi.Models.Interfaces.Entities.DeleteEnity;

public interface DControllerInstance<E extends DeleteEnity, ID extends Comparable<ID>> {
    DController<E, ID> Remove();


}
