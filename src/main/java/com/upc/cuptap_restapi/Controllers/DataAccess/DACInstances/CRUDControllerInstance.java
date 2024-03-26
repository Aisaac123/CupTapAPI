package com.upc.cuptap_restapi.Controllers.DataAccess.DACInstances;

import com.upc.cuptap_restapi.Models.Interfaces.CrudEntity;


public interface CRUDControllerInstance<E extends CrudEntity, ID extends Comparable<ID>>
        extends RControllerInstance<E, ID>, UControllerInstance<E, ID>, DControllerInstance<E, ID>, CControllerInstance<E, ID> {
}
