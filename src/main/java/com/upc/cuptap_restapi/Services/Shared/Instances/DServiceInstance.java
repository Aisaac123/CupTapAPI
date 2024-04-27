package com.upc.cuptap_restapi.Services.Shared.Instances;

import com.upc.cuptap_restapi.Models.Interfaces.Entities.DeleteEnity;
import com.upc.cuptap_restapi.Services.Shared.Implements.DService;

public interface DServiceInstance<E extends DeleteEnity, ID extends Comparable<ID>> {
    DService<E, ID> Remove();


}
