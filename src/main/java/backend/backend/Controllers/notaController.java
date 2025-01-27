package backend.backend.Controllers;

import org.springframework.web.bind.annotation.RestController;

import backend.backend.Entities.nota;
import backend.backend.Entities.DTO.notaDTO;
import backend.backend.Services.notaService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;

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
    //private final usuarioService usuarioService;

    //• Crear una nueva nota.
    @PostMapping
    @Operation(summary = "Crear una nota", description = "• Crear una nueva nota.")
    public void Crearnota(@RequestBody @Validated notaDTO notaDTO) {
        notaService.Crearnota(notaDTO);
    }
    //• Editar una nota existente. 
    @PutMapping
    @Operation(summary = "Modificar una nota", description = "• Editar una nota existente. ")
    public boolean Modificarnota(@RequestBody @Validated notaDTO notaDTO) {
        return notaService.Modificarnota(notaDTO);
    }
    //• Eliminar una nota. 
	@DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una nota", description = "• Eliminar una nota.")
	public boolean eliminarProducto(@PathVariable("id") int id) {
        var nota = notaService.Obtenernota(id);
		return notaService.Eliminarnota( nota);

	}

	@GetMapping
	public nota[] getAll() {
		return notaService.Obtenernotas();
	}

    //• Ver detalles de una nota específica. 
	@GetMapping("/{id}")
    @Operation(summary = "Obtener nota por id", description = "• Ver detalles de una nota específica. ")
	public nota getById(@PathVariable("id") int id) {
		return notaService.Obtenernota(id);
	}

    //• Listar todas las notas del usuario autenticado (con filtros opcionales). 
    @GetMapping("/Usuario/{usuarioId}")
    @Operation(summary = "Obtener notas por usuario", description = "• Listar todas las notas del usuario autenticado (con filtros opcionales).")
	public nota[] getAllByUserId(@PathVariable("usuarioId") int usuarioId) {
        
		return notaService.ObtenernotasByUserId(usuarioId);
	}
}
