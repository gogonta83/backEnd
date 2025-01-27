package backend.backend.Controllers;

import org.springframework.web.bind.annotation.RestController;

import backend.backend.Entities.etiqueta;
import backend.backend.Entities.DTO.etiquetaDTO;
import backend.backend.Services.etiquetaService;
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

@RestController
@RequestMapping("/etiqueta")
@RequiredArgsConstructor

public class etiquetaController {
    private final etiquetaService etiquetaService;

    //• Crear una nueva etiqueta. 
    @PostMapping
    @Operation(summary = "Crear una etiqueta", description = "• Crear una nueva etiqueta.")
    public void Crearetiqueta(@RequestBody @Validated etiquetaDTO etiqueta) {
        etiquetaService.Crearetiqueta(etiqueta);
    }

    @PutMapping
    public boolean Modificaretiqueta(@RequestBody @Validated etiquetaDTO etiqueta) {
        return etiquetaService.Modificaretiqueta(etiqueta);
    }
    //• Eliminar una etiqueta (opcionalmente con la opción de reasignar notas a otra etiqueta).
	@DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una etiqueta", description = "• Eliminar una etiqueta (opcionalmente con la opción de reasignar notas a otra etiqueta).")
	public boolean eliminarProducto(@PathVariable("id") int id) {
        var etiqueta = etiquetaService.Obteneretiqueta(id);
		return etiquetaService.Eliminaretiqueta( etiqueta);

	}

	@GetMapping
	public etiqueta[] getAll() {
		return etiquetaService.Obteneretiquetas();
	}

	@GetMapping("/id/{id}")
	public etiqueta getById(@PathVariable("id") int id) {
		return etiquetaService.Obteneretiqueta(id);
	}

    //• Obtener todas las etiquetas del usuario. 
    @GetMapping("/Usuario/{usuarioId}")
    @Operation(summary = "Obtener etiquetas por usuario", description = "• Obtener todas las etiquetas del usuario. ")
	public etiqueta[] getAllByUserId(@PathVariable("usuarioId") int usuarioId) {
        
		return etiquetaService.ObteneretiquetasByUserId(usuarioId);
	}
}
