package com.upc.cuptap_restapi.Controllers.Providers.ProvidersInstances;

import com.upc.cuptap_restapi.Controllers.Providers.Providers.DController;
import com.upc.cuptap_restapi.Models.Interfaces.Entities.DeleteEnity;

public interface DControllerInstance<E extends DeleteEnity, ID extends Comparable<ID>> {
    DController<E, ID> Remove();


}
