package com.upc.cuptap_restapi.Controllers.Controller;

import com.upc.cuptap_restapi.Controllers.DataAccess.DACInstances.CRUDControllerInstance;
import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.CController;
import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.DController;
import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.RController;
import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.UController;
import com.upc.cuptap_restapi.Models.DTO.ComboDto;
import com.upc.cuptap_restapi.Models.Entities.Combo;
import com.upc.cuptap_restapi.Models.Interfaces.DTO.IDTO;
import com.upc.cuptap_restapi.Models.Utilities.Response;
import com.upc.cuptap_restapi.Services.Bussiness.ComboService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/CupTapAPI/v1/Combos")
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


    @GetMapping("")
    @Operation(summary = "Consulta todos los combos registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Muestra los datos de todos los combos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<List<Combo>>> GetAll() {
        return Read().GetAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulta combos por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Muestra los datos del combo correspondiente"),
            @ApiResponse(responseCode = "404", description = "No se encontro el usuario por id", content = {@Content(schema = @Schema(implementation = Response.Doc.NotFound.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Combo>> GetById(@PathVariable String id) {
        return Read().GetById(id);
    }

    @GetMapping("/{page_index}/{limit}")
    @Operation(summary = "Consulta de combos (Paginacion)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Muestra la pagina con los combos solicitados"),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta (JSON invalido)", content = {@Content(schema = @Schema(implementation = Response.Doc.BadRequest.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Page<Combo>>> GetPageable(@PathVariable int page_index, @PathVariable int limit) {
        return Read().GetPageable(page_index, limit);
    }

    @PostMapping("")
    @Operation(summary = "Permite registrar combos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se agregó correctamente al combo"),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta (JSON invalido)", content = {@Content(schema = @Schema(implementation = Response.Doc.BadRequest.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Combo>> Save(@RequestBody ComboDto combo) {
        return Persist().Save(combo.toEntity());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Permite actualizar los datos del combo por medio de su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se actualizo correctamente los datos del combo"),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta (JSON invalido)", content = {@Content(schema = @Schema(implementation = Response.Doc.BadRequest.class))}),
            @ApiResponse(responseCode = "404", description = "No se encontro el usuario por id", content = {@Content(schema = @Schema(implementation = Response.Doc.NotFound.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Map<String, Combo>>> Update(@PathVariable String id, @RequestBody ComboDto new_combo) {
        return Modify().Update(new_combo.toEntity(), id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Permite eliminar combos por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se eliminó correctamente al combo"),
            @ApiResponse(responseCode = "404", description = "No se encontro el usuario por id", content = {@Content(schema = @Schema(implementation = Response.Doc.NotFound.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Combo>> Delete(@PathVariable String id) {
        return Remove().Delete(id);
    }

    // TODO Controladores especificos



}
