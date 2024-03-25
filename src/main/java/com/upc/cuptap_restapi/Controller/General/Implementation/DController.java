package com.upc.cuptap_restapi.Controller.General.Implementation;

import com.upc.cuptap_restapi.Controller.General.Interfaces.IDController;
import com.upc.cuptap_restapi.Models.Interface.DeleteEnity;
import com.upc.cuptap_restapi.Models.Utilities.Response;
import com.upc.cuptap_restapi.Models.Utilities.ResponseBuilder;
import com.upc.cuptap_restapi.Services.General.Instances.DServiceInstance;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class DController<E extends DeleteEnity, ID extends Comparable<ID>> implements IDController<E, ID> {

    private DServiceInstance<E, ID> service;

    public DController<E, ID> setService(DServiceInstance<E, ID> service) {
        this.service = service;
        return this;
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Response<E>> Delete(@PathVariable ID id) {
        try {
            var response = service.Remove().Delete(id);
            HttpStatus status = response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(response, status);
        } catch (Exception e) {
            return new ResponseEntity<>(ResponseBuilder.Error(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
