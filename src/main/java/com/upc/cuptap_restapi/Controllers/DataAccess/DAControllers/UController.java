package com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers;

import com.upc.cuptap_restapi.Controllers.DataAccess.DAInterfaces.IUController;
import com.upc.cuptap_restapi.Models.Interfaces.UpdateEntity;
import com.upc.cuptap_restapi.Models.Utilities.Response;
import com.upc.cuptap_restapi.Models.Utilities.ResponseBuilder;
import com.upc.cuptap_restapi.Services.DataAccess.DAServices.Intefaces.IUService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
public class UController<E extends UpdateEntity, ID extends Comparable<ID>> implements IUController<E, ID> {


    private IUService<E, ID> service;

    public UController<E, ID> setService(IUService<E, ID> service) {
        this.service = service;
        return this;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<Map<String, E>>> Update(@RequestBody E item, @PathVariable ID id) {
        try {
            var response = service.Update(item, id);
            HttpStatus status = response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(response, status);
        } catch (Exception e) {
            return new ResponseEntity<>(ResponseBuilder.Error(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
