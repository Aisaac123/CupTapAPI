package com.upc.cuptap_restapi.Controllers.Controller;

import com.upc.cuptap_restapi.Controllers.DataAccess.DACInstances.CRUDControllerInstance;
import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.CController;
import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.DController;
import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.RController;
import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.UController;
import com.upc.cuptap_restapi.Models.Entities.Usuario;
import com.upc.cuptap_restapi.Models.Utilities.Response;
import com.upc.cuptap_restapi.Services.Service.UsuarioService;
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
@RequestMapping("/CupTapAPI/v1/Usuarios")
@Tag(name = "Usuarios", description = "Controlador del modulo de usuarios")

public class UsuarioController implements CRUDControllerInstance<Usuario, String> {


    private final UsuarioService service;

    public UsuarioController(UsuarioService usuarioService) {
        service = usuarioService;
    }

    @Override
    public CController<Usuario, String> Persist() {
        return new CController<>(service.Persist());
    }

    @Override
    public DController<Usuario, String> Remove() {
        return new DController<>(service.Remove());
    }

    @Override
    public RController<Usuario, String> Read() {
        return new RController<>(service.Read());
    }

    @Override
    public UController<Usuario, String> Modify() {
        return new UController<>(service.Modify());
    }

    @GetMapping("")
    @Operation(summary = "Consulta todos los usuarios registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Muestra los datos de todos los usuarios"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<List<Usuario>>> GetAll() {
        return Read().GetAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulta usuario por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Muestra los datos del usuario correspondiente"),
            @ApiResponse(responseCode = "404", description = "No se encontro el usuario por id", content = {@Content(schema = @Schema(implementation = Response.Doc.NotFound.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Usuario>> GetById(@PathVariable String id) {
        return Read().GetById(id);
    }

    @GetMapping("/{page_index}/{limit}")
    @Operation(summary = "Consulta de usuarios (Paginacion)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Muestra la pagina con los usuarios solicitados"),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta (JSON invalido)", content = {@Content(schema = @Schema(implementation = Response.Doc.BadRequest.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Page<Usuario>>> GetPageable(@PathVariable int page_index, @PathVariable int limit) {
        return Read().GetPageable(page_index, limit);
    }

    @PostMapping("")
    @Operation(summary = "Permite registrar usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se agregó correctamente al usuario"),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta (JSON invalido)", content = {@Content(schema = @Schema(implementation = Response.Doc.BadRequest.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Usuario>> Save(@RequestBody Usuario usuario) {
        return Persist().Save(usuario);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Permite actualizar los datos del Usuario por medio de su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se actualizo correctamente los datos del usuario"),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta (JSON invalido)", content = {@Content(schema = @Schema(implementation = Response.Doc.BadRequest.class))}),
            @ApiResponse(responseCode = "404", description = "No se encontro el usuario por id", content = {@Content(schema = @Schema(implementation = Response.Doc.NotFound.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Map<String, Usuario>>> Update(@PathVariable String id, @RequestBody Usuario new_user) {
        return Modify().Update(new_user, id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Permite eliminar Usuario por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se eliminó correctamente al usuario"),
            @ApiResponse(responseCode = "404", description = "No se encontro el usuario por id", content = {@Content(schema = @Schema(implementation = Response.Doc.NotFound.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Usuario>> Delete(@PathVariable String id) {
        return Remove().Delete(id);
    }
}
