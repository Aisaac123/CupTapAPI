package com.upc.cuptap_restapi.Controllers.Controller;

import com.upc.cuptap_restapi.Controllers.Providers.Providers.CController;
import com.upc.cuptap_restapi.Controllers.Providers.Providers.DController;
import com.upc.cuptap_restapi.Controllers.Providers.Providers.RController;
import com.upc.cuptap_restapi.Controllers.Providers.Providers.UController;
import com.upc.cuptap_restapi.Controllers.Providers.ProvidersInstances.CRUDControllerInstance;
import com.upc.cuptap_restapi.Models.DTO.DTORequest.PromocionRequest;
import com.upc.cuptap_restapi.Models.Entities.Promocion;
import com.upc.cuptap_restapi.Models.Utils.Response;
import com.upc.cuptap_restapi.Services.Logic.PromocionService;
import com.upc.cuptap_restapi.Services.Middlewares.ReconstructMiddleware;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/v1/Promociones")
@Tag(name = "Promociones", description = "Controlador del modulo de promociones")
public class PromocionController implements CRUDControllerInstance<Promocion, Long> {
    final
    PromocionService serv;
    @Autowired
    ReconstructMiddleware reconstruct;

    public PromocionController(PromocionService serv) {
        this.serv = serv;
    }

    @Override
    public CController<Promocion, Long> Persist() {
        return new CController<>(serv.Persist());
    }

    @Override
    public DController<Promocion, Long> Remove() {
        return new DController<>(serv.Remove());
    }

    @Override
    public RController<Promocion, Long> Read() {
        return new RController<>(serv.Read());
    }

    @Override
    public UController<Promocion, Long> Modify() {
        return new UController<>(serv.Modify());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulta de promociones por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Muestra los datos de las promociones correspondiente"),
            @ApiResponse(responseCode = "404", description = "No se encontro la promocion por id", content = {@Content(schema = @Schema(implementation = Response.Doc.NotFound.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Promocion>> GetById(@PathVariable Long id, @RequestParam(value = "lazy", required = false) boolean isLazy) {
        return Read().GetById(id, isLazy);
    }

    @GetMapping("")
    @Operation(summary = "Consulta de promociones (Paginacion)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Muestra la pagina con las promociones solicitadas"),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta (JSON invalido)", content = {@Content(schema = @Schema(implementation = Response.Doc.BadRequest.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response> GetAll(@RequestParam(value = "index", defaultValue = "-1", required = false) int page_index,
                                           @RequestParam(value = "limit", defaultValue = "-1", required = false) int limit,
                                           @RequestParam(value = "dateLimit", required = false) LocalDate fecha,
                                           @RequestParam(value = "lazy", required = false) boolean isLazy) {
        return Read().GetAll(page_index, limit, fecha, isLazy);
    }

    @PostMapping("")
    @Operation(summary = "Permite registrar promociones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se agregó correctamente la promocion"),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta (JSON invalido)", content = {@Content(schema = @Schema(implementation = Response.Doc.BadRequest.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Promocion>> Save(@RequestBody PromocionRequest entity) {
        return Persist().Save(reconstruct.reconstruct(entity));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Permite actualizar los datos de la promocion por medio de su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se actualizo correctamente los datos de la promocion"),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta (JSON invalido)", content = {@Content(schema = @Schema(implementation = Response.Doc.BadRequest.class))}),
            @ApiResponse(responseCode = "404", description = "No se encontro la promocion por id", content = {@Content(schema = @Schema(implementation = Response.Doc.NotFound.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Map<String, Promocion>>> Update(@PathVariable Long id, @RequestBody PromocionRequest new_entity) {
        return Modify().Update(reconstruct.reconstruct(new_entity), id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Permite eliminar la promociones por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se eliminó correctamente la promocion"),
            @ApiResponse(responseCode = "404", description = "No se encontro la promocion por id", content = {@Content(schema = @Schema(implementation = Response.Doc.NotFound.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Promocion>> Delete(@PathVariable Long id) {
        return Remove().Delete(id);
    }

    // TODO Controladores especificos

}
