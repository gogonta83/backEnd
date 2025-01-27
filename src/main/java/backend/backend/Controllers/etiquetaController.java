package backend.backend.Controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import backend.backend.Entities.etiqueta;
import backend.backend.Entities.DTO.etiquetaDTO;
import backend.backend.Services.etiquetaService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/etiqueta")
@RequiredArgsConstructor
public class etiquetaController {
    private final etiquetaService etiquetaService;

    // • Crear una nueva etiqueta.
    @PostMapping
    @Operation(summary = "Crear una etiqueta", description = "• Crear una nueva etiqueta.")
    public ResponseEntity<String> CrearEtiqueta(@RequestBody @Validated etiquetaDTO etiqueta) {
        etiquetaService.Crearetiqueta(etiqueta);
        return ResponseEntity.status(HttpStatus.CREATED).body("Etiqueta creada exitosamente");
    }

    @PutMapping
    @Operation(summary = "Modificar una etiqueta", description = "• Modificación de una etiqueta existente.")
    public ResponseEntity<String> ModificarEtiqueta(@RequestBody @Validated etiquetaDTO etiqueta) {
        boolean resultado = etiquetaService.Modificaretiqueta(etiqueta);
        if (resultado) {
            return ResponseEntity.ok("Etiqueta modificada exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Etiqueta no encontrada");
        }
    }

    // • Eliminar una etiqueta (opcionalmente con la opción de reasignar notas a
    // otra etiqueta).
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una etiqueta", description = "• Eliminar una etiqueta (opcionalmente con la opción de reasignar notas a otra etiqueta).")
    public ResponseEntity<String> EliminarEtiqueta(@PathVariable("id") int id) {
        if (id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID inválido");
        }

        var etiqueta = etiquetaService.Obteneretiqueta(id);
        if (etiqueta == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Etiqueta no encontrada");
        }

        boolean resultado = etiquetaService.Eliminaretiqueta(etiqueta);
        if (resultado) {
            return ResponseEntity.ok("Etiqueta eliminada exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la etiqueta");
        }
    }

    @GetMapping
    @Operation(summary = "Obtener todas las etiquetas", description = "• Obtener todas las etiquetas.")
    public ResponseEntity<etiqueta[]> ObtenerEtiquetas() {
        etiqueta[] etiquetas = etiquetaService.Obteneretiquetas();
        return ResponseEntity.ok(etiquetas);
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Obtener etiqueta por id", description = "• Ver detalles de una etiqueta específica.")
    public ResponseEntity<etiqueta> GetById(@PathVariable("id") int id) {
        if (id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID inválido");
        }

        var etiqueta = etiquetaService.Obteneretiqueta(id);
        if (etiqueta == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Etiqueta no encontrada");
        }

        return ResponseEntity.ok(etiqueta);
    }

    // • Obtener todas las etiquetas del usuario.
    @GetMapping("/Usuario/{usuarioId}")
    @Operation(summary = "Obtener etiquetas por usuario", description = "• Obtener todas las etiquetas del usuario.")
    public ResponseEntity<etiqueta[]> GetAllByUserId(@PathVariable("usuarioId") int usuarioId) {
        if (usuarioId <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID de usuario inválido");
        }

        var etiquetas = etiquetaService.ObteneretiquetasByUserId(usuarioId);
        if (etiquetas == null || etiquetas.length == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Etiquetas no encontradas");
        }

        return ResponseEntity.ok(etiquetas);
    }
}
