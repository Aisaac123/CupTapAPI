package com.upc.cuptap_restapi.Controllers.Controller;

import com.upc.cuptap_restapi.Controllers.Shared.Implements.CController;
import com.upc.cuptap_restapi.Controllers.Shared.Implements.DController;
import com.upc.cuptap_restapi.Controllers.Shared.Implements.RController;
import com.upc.cuptap_restapi.Controllers.Shared.Implements.UController;
import com.upc.cuptap_restapi.Controllers.Shared.Instances.CRUDControllerInstance;
import com.upc.cuptap_restapi.Models.DTO.DTORequest.ComboRequest;
import com.upc.cuptap_restapi.Models.Entities.Combo;
import com.upc.cuptap_restapi.Models.Utils.Response;
import com.upc.cuptap_restapi.Services.Logic.ComboService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

/**
 * ComboController is a REST controller which maps to "/v1/Combos" endpoint.
 * It is responsible for handling CRUD operations on Combo objects.
 * It implements CRUDControllerInstance interface with Combo as entity and String as key type.
 *
 * Each method in this controller aligns with a HTTP method:
 * - CREATE: Save (POST)
 * - READ: GetById (GET by id), GetAll (GET all)
 * - UPDATE: Update (PUT by id)
 * - DELETE: Delete (DELETE by id)
 *
 * HTTP response codes are provided through @ApiResponses annotations. Common response codes include 200 (OK),
 * 400 (Bad request), 404 (Not found) and 500 (Internal Server Error).
 *
 * @author <ISAAC>
 * @version 1.0
 * @since <28-04-2024>
 */

@RestController
@RequestMapping("/v1/Combos")
@Tag(name = "Combos", description = "Controlador del modulo de Combos")
public class ComboController implements CRUDControllerInstance<Combo, String> {

    final
    ComboService serv;



    public ComboController(ComboService serv) {
        this.serv = serv;
    }

    @Override
    public CController<Combo, String> Persist() {
        return new CController<>(serv.Persist());
    }

    @Override
    public DController<Combo, String> Remove() {
        return new DController<>(serv.Remove());
    }

    @Override
    public RController<Combo, String> Read() {
        return new RController<>(serv.Read());
    }

    @Override
    public UController<Combo, String> Modify() {
        return new UController<>(serv.Modify());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulta combos por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Muestra los datos del combo correspondiente"),
            @ApiResponse(responseCode = "404", description = "No se encontro el combo por id", content = {@Content(schema = @Schema(implementation = Response.Doc.NotFound.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response> GetById(@PathVariable String id, @RequestParam(value = "lazy", required = false) boolean isLazy) {
        return Read().GetById(id, isLazy);
    }

    @GetMapping("")
    @Operation(summary = "Consulta de combos (Paginacion)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Muestra la pagina con los combos solicitados"),
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
    @Operation(summary = "Permite registrar combos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se agregó correctamente al combo"),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta (JSON invalido)", content = {@Content(schema = @Schema(implementation = Response.Doc.BadRequest.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Combo>> Save(@RequestBody ComboRequest combo) {
        return Persist().Save(serv.reconstruct(combo));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Permite actualizar los datos del combo por medio de su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se actualizo correctamente los datos del combo"),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta (JSON invalido)", content = {@Content(schema = @Schema(implementation = Response.Doc.BadRequest.class))}),
            @ApiResponse(responseCode = "404", description = "No se encontro el combo por id", content = {@Content(schema = @Schema(implementation = Response.Doc.NotFound.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Map<String, Combo>>> Update(@PathVariable String id, @RequestBody ComboRequest new_combo) {
        return Modify().Update(serv.reconstruct(new_combo), id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Permite eliminar combos por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se eliminó correctamente al combo"),
            @ApiResponse(responseCode = "404", description = "No se encontro el combo por id", content = {@Content(schema = @Schema(implementation = Response.Doc.NotFound.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Combo>> Delete(@PathVariable String id) {
        return Remove().Delete(id);
    }

    // TODO Controladores especificos


}
