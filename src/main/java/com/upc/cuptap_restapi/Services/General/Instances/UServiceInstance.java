package com.upc.cuptap_restapi.Services.General.Instances;

import com.upc.cuptap_restapi.Models.Interface.UpdateEntity;
import com.upc.cuptap_restapi.Services.General.GenericServices.UService;

public interface UServiceInstance<E extends UpdateEntity, ID extends Comparable<ID>> {
    UService<E, ID> Modify();


}
