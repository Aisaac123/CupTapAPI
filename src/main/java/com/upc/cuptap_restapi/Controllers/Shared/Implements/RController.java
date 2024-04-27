package com.upc.cuptap_restapi.Controllers.Shared.Implements;

import com.upc.cuptap_restapi.Controllers.Shared.Interfaces.IRController;
import com.upc.cuptap_restapi.Models.Interfaces.Entities.ReadEntity;
import com.upc.cuptap_restapi.Models.Utils.PageParams;
import com.upc.cuptap_restapi.Models.Utils.Response;
import com.upc.cuptap_restapi.Models.Utils.ResponseBuilder;
import com.upc.cuptap_restapi.Services.Shared.Interfaces.IRService;
import com.upc.cuptap_restapi.Services.Utils.Options.ReadingOptions;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
public class RController<E extends ReadEntity, ID extends Comparable<ID>> implements IRController<E, ID> {

    IRService<E, ID> service;

    public RController<E, ID> setService(IRService<E, ID> service) {
        this.service = service;
        return this;
    }

    @Override
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "No se encontro el item por id", content = {@Content(schema = @Schema(implementation = Response.Doc.NotFound.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response> GetById(@PathVariable ID id, @RequestParam(value = "lazy", required = false) boolean isLazy) {
        try {
            var response = service.GetById(id, new ReadingOptions().withLazy(isLazy));
            var data = response.getData();
            if (data == null)
                return new ResponseEntity<>(ResponseBuilder.Fail("No se ha encontrado el elemento"), HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(ResponseBuilder.Error(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<Response> GetAll(@RequestParam(value = "index", defaultValue = "-1", required = false) int page_index,
                                           @RequestParam(value = "limit", defaultValue = "-1", required = false) int limit,
                                           @RequestParam(value = "dateLimit", required = false) LocalDate fecha,
                                           @RequestParam(value = "lazy", required = false) boolean isLazy) {
        try {
            if (page_index == -1 && limit == -1)
                return new ResponseEntity<>(service.GetAll(new ReadingOptions()
                        .withPageParams(new PageParams(limit, page_index - 1))
                        .withDateLimit(fecha)
                        .withLazy(isLazy)),
                        HttpStatus.OK);
            else if (page_index < 1)
                return new ResponseEntity<>(ResponseBuilder.Fail("el indice de la pagina no puede estar vacia o ser menor a 1"), HttpStatus.BAD_REQUEST);
            else if (limit < 1)
                return new ResponseEntity<>(ResponseBuilder.Fail("el limite de datos a traer no puede estar vacio o ser menor a 1"), HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(service.GetPages(new ReadingOptions()
                    .withPageParams(new PageParams(limit, page_index - 1))
                    .withDateLimit(fecha)
                    .withLazy(isLazy)),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(ResponseBuilder.Error(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
