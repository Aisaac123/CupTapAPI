package com.upc.cuptap_restapi.Controllers.DataAccess.DAInterfaces;

import com.upc.cuptap_restapi.Models.Interfaces.DeleteEnity;
import com.upc.cuptap_restapi.Models.Utilities.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface IDController<E extends DeleteEnity, ID extends Comparable<ID>> extends Controller {

    ResponseEntity<Response<E>> Delete(@PathVariable ID id);

}
