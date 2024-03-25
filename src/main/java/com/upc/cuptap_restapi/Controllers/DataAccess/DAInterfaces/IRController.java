package com.upc.cuptap_restapi.Controllers.DataAccess.DAInterfaces;

import com.upc.cuptap_restapi.Models.Interfaces.ReadEntity;
import com.upc.cuptap_restapi.Models.Utilities.Response;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IRController<E extends ReadEntity, ID extends Comparable<ID>> extends Controller {
    ResponseEntity<Response<List<E>>> GetAll();

    ResponseEntity<Response<E>> GetById(@PathVariable ID id);

    ResponseEntity<Response<Page<E>>> GetPageable(@PathVariable int page_index, @PathVariable int limit);

}
