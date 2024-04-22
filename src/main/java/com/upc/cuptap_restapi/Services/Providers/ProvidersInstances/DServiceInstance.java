package com.upc.cuptap_restapi.Services.Providers.ProvidersInstances;

import com.upc.cuptap_restapi.Models.Interfaces.Entities.DeleteEnity;
import com.upc.cuptap_restapi.Services.Providers.Providers.Implements.DService;

public interface DServiceInstance<E extends DeleteEnity, ID extends Comparable<ID>> {
    DService<E, ID> Remove();


}
