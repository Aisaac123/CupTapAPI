package com.upc.cuptap_restapi.Services.General.Instances;

import com.upc.cuptap_restapi.Models.Interface.ReadEntity;
import com.upc.cuptap_restapi.Services.General.GenericServices.RService;

public interface RServiceInstance<E extends ReadEntity, ID extends Comparable<ID>> {
    RService<E, ID> Read();
}
