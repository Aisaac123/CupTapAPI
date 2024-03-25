package com.upc.cuptap_restapi.Services.General.Instances;

import com.upc.cuptap_restapi.Models.Interface.CrudEntity;
import com.upc.cuptap_restapi.Services.General.GenericServices.Intefaces.Services;


public interface CRUDServiceInstance<E extends CrudEntity, ID extends Comparable<ID>>
        extends Services,
        RServiceInstance<E, ID>,
        UServiceInstance<E, ID>,
        DServiceInstance<E, ID>,
        CServiceInstance<E, ID> {
}
