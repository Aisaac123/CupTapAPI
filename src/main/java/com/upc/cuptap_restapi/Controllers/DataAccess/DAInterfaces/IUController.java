package com.upc.cuptap_restapi.Controllers.DataAccess.DAInterfaces;

import com.upc.cuptap_restapi.Models.Interfaces.Entities.UpdateEntity;
import com.upc.cuptap_restapi.Models.Utilities.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

public interface IUController<E extends UpdateEntity, ID extends Comparable<ID>> extends Controller {
    ResponseEntity<Response<Map<String, E>>> Update(@RequestBody E item, @PathVariable ID id);

}
