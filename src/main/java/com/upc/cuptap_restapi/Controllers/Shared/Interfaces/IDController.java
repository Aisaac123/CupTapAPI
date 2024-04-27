package com.upc.cuptap_restapi.Controllers.Shared.Interfaces;

import com.upc.cuptap_restapi.Models.Interfaces.Entities.DeleteEnity;
import com.upc.cuptap_restapi.Models.Utils.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface IDController<E extends DeleteEnity, ID extends Comparable<ID>> extends Controller {

    default ResponseEntity<Response<E>> Delete(@PathVariable ID id){
        return Delete(id, false);
    }

    ResponseEntity<Response<E>> Delete(@PathVariable ID id, boolean transmit);


}
