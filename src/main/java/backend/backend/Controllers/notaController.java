package backend.backend.Controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import backend.backend.Entities.nota;
import backend.backend.Entities.DTO.notaDTO;
import backend.backend.Services.notaService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//2. Gestión de Notas
@RestController
@RequestMapping("/nota")
@RequiredArgsConstructor

public class notaController {
    private final notaService notaService;

    // • Crear una nueva nota.
    @PostMapping
    @Operation(summary = "Crear una nota", description = "• Crear una nueva nota.")
    public ResponseEntity<String> Crearnota(@RequestBody @Validated notaDTO notaDTO) {
        notaService.Crearnota(notaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Nota creada exitosamente");
    }

    // • Editar una nota existente.
    @PutMapping
    @Operation(summary = "Modificar una nota", description = "• Editar una nota existente.")
    public ResponseEntity<String> Modificarnota(@RequestBody @Validated notaDTO notaDTO) {
        boolean resultado = notaService.Modificarnota(notaDTO);
        if (resultado) {
            return ResponseEntity.ok("Nota modificada exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nota no encontrada");
        }
    }

    // • Eliminar una nota.
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una nota", description = "• Eliminar una nota.")
    public ResponseEntity<String> EliminarNota(@PathVariable("id") int id) {
        if (id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID inválido");
        }

        var nota = notaService.Obtenernota(id);
        if (nota == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nota no encontrada");
        }

        boolean resultado = notaService.Eliminarnota(nota);
        if (resultado) {
            return ResponseEntity.ok("Nota eliminada exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la nota");
        }
    }

    @GetMapping
    @Operation(summary = "Obtener todas las notas", description = "• Obtención de todas las notas.")
    public ResponseEntity<nota[]> ObtenerNotas() {
        nota[] notas = notaService.Obtenernotas();
        return ResponseEntity.ok(notas);
    }

    // • Ver detalles de una nota específica.
    @GetMapping("/{id}")
    @Operation(summary = "Obtener nota por id", description = "• Ver detalles de una nota específica.")
    public ResponseEntity<nota> GetById(@PathVariable("id") int id) {
        if (id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID inválido");
        }

        var nota = notaService.Obtenernota(id);
        if (nota == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nota no encontrada");
        }

        return ResponseEntity.ok(nota);
    }

    // • Listar todas las notas del usuario autenticado (con filtros opcionales).
    @GetMapping("/Usuario/{usuarioId}")
    @Operation(summary = "Obtener notas por usuario", description = "• Listar todas las notas del usuario autenticado (con filtros opcionales).")
    public nota[] GetAllByUserId(@PathVariable("usuarioId") int usuarioId) {

        if (usuarioId <= 0)
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "invalid user id");

        var notas = notaService.ObtenernotasByUserId(usuarioId);

        if (notas == null || notas.length == 0)
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "not found");

        return notas;
    }
}
