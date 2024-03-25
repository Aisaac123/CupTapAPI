package com.upc.cuptap_restapi.Controllers.DataAccess.DACInstances;

import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.DController;
import com.upc.cuptap_restapi.Models.Interfaces.DeleteEnity;

public interface DControllerInstance<E extends DeleteEnity, ID extends Comparable<ID>> {
    DController<E, ID> Remove();


}
