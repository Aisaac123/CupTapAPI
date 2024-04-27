package com.upc.cuptap_restapi.Controllers.Shared.Interfaces;

import com.upc.cuptap_restapi.Models.Interfaces.Entities.UpdateEntity;
import com.upc.cuptap_restapi.Models.Utils.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

public interface IUController<E extends UpdateEntity, ID extends Comparable<ID>> extends Controller {
    default ResponseEntity<Response<Map<String, E>>> Update(@RequestBody E item, @PathVariable ID id){
        return Update(item, id, false);
    }

    ResponseEntity<Response<Map<String, E>>> Update(@RequestBody E item, @PathVariable ID id, boolean transmit);


}
