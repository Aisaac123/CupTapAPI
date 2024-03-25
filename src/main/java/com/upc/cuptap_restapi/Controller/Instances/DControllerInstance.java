package com.upc.cuptap_restapi.Controller.Instances;

import com.upc.cuptap_restapi.Controller.General.Implementation.DController;
import com.upc.cuptap_restapi.Models.Interface.DeleteEnity;

public interface DControllerInstance<E extends DeleteEnity, ID extends Comparable<ID>> {
    DController<E, ID> Remove();


}
