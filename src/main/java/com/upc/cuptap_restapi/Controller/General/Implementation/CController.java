package com.upc.cuptap_restapi.Controller.General.Implementation;

import com.upc.cuptap_restapi.Controller.General.Interfaces.ICController;
import com.upc.cuptap_restapi.Models.Interface.CreateEntity;
import com.upc.cuptap_restapi.Models.Utilities.Response;
import com.upc.cuptap_restapi.Models.Utilities.ResponseBuilder;
import com.upc.cuptap_restapi.Services.General.Instances.CServiceInstance;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


public class CController<E extends CreateEntity, ID extends Comparable<ID>> implements ICController<E> {

    private CServiceInstance<E, ID> service;

    public CController<E, ID> setService(CServiceInstance<E, ID> service) {
        this.service = service;
        return this;
    }

    @PostMapping("/añadir")
    public ResponseEntity<Response<E>> Save(@RequestBody E item) {
        try {
            var response = service.Persist().Save(item);
            HttpStatus status = response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(response, status);
        } catch (Exception e) {
            return new ResponseEntity<>(ResponseBuilder.Error(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
