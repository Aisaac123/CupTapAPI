package com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers;

import com.upc.cuptap_restapi.Controllers.DataAccess.DAInterfaces.IDController;
import com.upc.cuptap_restapi.Models.Interfaces.DeleteEnity;
import com.upc.cuptap_restapi.Models.Utilities.Response;
import com.upc.cuptap_restapi.Models.Utilities.ResponseBuilder;
import com.upc.cuptap_restapi.Services.DataAccess.DASIntances.DServiceInstance;
import com.upc.cuptap_restapi.Services.DataAccess.DAServices.Intefaces.IDService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@NoArgsConstructor
@AllArgsConstructor
public class DController<E extends DeleteEnity, ID extends Comparable<ID>> implements IDController<E, ID> {

    private IDService<E, ID> service;

    public DController<E, ID> setService(IDService<E, ID> service) {
        this.service = service;
        return this;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<E>> Delete(@PathVariable ID id) {
        try {
            var response = service.Delete(id);
            HttpStatus status = response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(response, status);
        } catch (Exception e) {
            return new ResponseEntity<>(ResponseBuilder.Error(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
