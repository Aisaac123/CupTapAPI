package com.upc.cuptap_restapi.Controllers.Shared.Implements;

import com.upc.cuptap_restapi.Controllers.Shared.Interfaces.ICController;
import com.upc.cuptap_restapi.Models.Interfaces.Entities.CreateEntity;
import com.upc.cuptap_restapi.Models.Utils.Response;
import com.upc.cuptap_restapi.Models.Utils.ResponseBuilder;
import com.upc.cuptap_restapi.Services.Shared.Interfaces.ICService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@NoArgsConstructor
@AllArgsConstructor
public class CController<E extends CreateEntity, ID extends Comparable<ID>> implements ICController<E> {

    private ICService<E> service;

    public CController<E, ID> setService(ICService<E> service) {
        this.service = service;
        return this;
    }

    @PostMapping("")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta (JSON invalido)", content = {@Content(schema = @Schema(implementation = Response.Doc.BadRequest.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    @Transactional
    public ResponseEntity<Response<E>> Save(@RequestBody E item, boolean transmit) {
        try {
            var response = service.Save(item, transmit);
            HttpStatus status = response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(response, status);
        } catch (Exception e) {
            return new ResponseEntity<>(ResponseBuilder.Error(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
