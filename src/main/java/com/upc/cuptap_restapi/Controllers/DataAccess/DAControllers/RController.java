package com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers;

import com.upc.cuptap_restapi.Controllers.DataAccess.DAInterfaces.IRController;
import com.upc.cuptap_restapi.Models.Interfaces.Entities.ReadEntity;
import com.upc.cuptap_restapi.Models.Utilities.Response;
import com.upc.cuptap_restapi.Models.Utilities.ResponseBuilder;
import com.upc.cuptap_restapi.Services.DataAccess.DAServices.Intefaces.IRService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class RController<E extends ReadEntity, ID extends Comparable<ID>> implements IRController<E, ID> {

    IRService<E, ID> service;

    public RController<E, ID> setService(IRService<E, ID> service) {
        this.service = service;
        return this;
    }

    @GetMapping("")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<List<E>>> GetAll() {
        try {
            return new ResponseEntity<>(service.GetAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(ResponseBuilder.Error(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "No se encontro el item por id", content = {@Content(schema = @Schema(implementation = Response.Doc.NotFound.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<E>> GetById(@PathVariable ID id) {
        try {
            var response = service.GetById(id);
            var data = response.getData();
            if (data == null)
                return new ResponseEntity<>(ResponseBuilder.Fail("No se ha encontrado el elemento"), HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(ResponseBuilder.Error(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{page_index}/{limit}")
    @Override
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta (JSON invalido)", content = {@Content(schema = @Schema(implementation = Response.Doc.BadRequest.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Page<E>>> GetPageable(@PathVariable int page_index, @PathVariable int limit) {
        try {
            return new ResponseEntity<>(service.GetPages(page_index, limit), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(ResponseBuilder.Error(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{fecha}/{page_index}/{limit}")
    public ResponseEntity<Response<Page<E>>> GetPageable(@PathVariable int page_index, @PathVariable int limit, @PathVariable("fecha") LocalDate fecha) {
        try {
            return new ResponseEntity<>(service.GetPages(page_index, limit, fecha), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(ResponseBuilder.Error(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
