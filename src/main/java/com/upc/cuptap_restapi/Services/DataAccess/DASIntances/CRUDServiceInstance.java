package com.upc.cuptap_restapi.Services.DataAccess.DASIntances;

import com.upc.cuptap_restapi.Models.Interfaces.Entities.CrudEntity;


public interface CRUDServiceInstance<E extends CrudEntity, ID extends Comparable<ID>>
        extends
        RServiceInstance<E, ID>,
        UServiceInstance<E, ID>,
        DServiceInstance<E, ID>,
        CServiceInstance<E, ID> {
}
