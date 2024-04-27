package com.upc.cuptap_restapi.Controllers.Shared.Interfaces;

import com.upc.cuptap_restapi.Models.Interfaces.Entities.ReadEntity;
import com.upc.cuptap_restapi.Models.Utils.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

public interface IRController<E extends ReadEntity, ID extends Comparable<ID>> extends Controller {
    ResponseEntity<Response> GetById(@PathVariable ID id, @RequestParam(value = "lazy", required = false) boolean isLazy);

    ResponseEntity<Response> GetAll(@PathVariable int page_index,
                                    @PathVariable int limit,
                                    @RequestParam(value = "fecha", required = false) LocalDate fecha,
                                    @RequestParam(value = "lazy", required = false) boolean isLazy);

}
