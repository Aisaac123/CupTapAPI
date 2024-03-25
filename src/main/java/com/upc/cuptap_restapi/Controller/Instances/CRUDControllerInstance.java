package com.upc.cuptap_restapi.Controller.Instances;

import com.upc.cuptap_restapi.Models.Interface.CrudEntity;
import com.upc.cuptap_restapi.Services.General.GenericServices.Intefaces.Services;


public interface CRUDControllerInstance<E extends CrudEntity, ID extends Comparable<ID>>
        extends Services, RControllerInstance<E, ID>, UControllerInstance<E, ID>, DControllerInstance<E, ID>, CControllerInstance<E, ID> {
}
