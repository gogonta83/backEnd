package backend.backend.Controllers;

import org.springframework.web.bind.annotation.RestController;

import backend.backend.Entities.usuario;
import backend.backend.Services.usuarioService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//1. Gestión de Usuarios 
@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor

public class usuarioController {
    private final usuarioService usuarioService;

	//• Registro de nuevos usuarios.
    @PostMapping
	@Operation(summary = "Crear un usuario", description = "• Registro de nuevos usuarios.")
    public void CrearUsuario(@RequestBody @Validated usuario usuario) {
        usuarioService.CrearUsuario(usuario);
    }

    @PutMapping
    public boolean ModificarUsuario(@RequestBody @Validated usuario usuario) {
        return usuarioService.ModificarUsuario(usuario);
    }

	@DeleteMapping("/{id}")
	public boolean eliminarProducto(@PathVariable("id") int id) {
        var usuario = usuarioService.ObtenerUsuario(id);
		return usuarioService.EliminarUsuario( usuario);

	}

	@GetMapping
	public usuario[] getAll() {
		return usuarioService.ObtenerUsuarios();
	}

	@GetMapping("/{id}")
	@Operation(summary = "Obtiene a un usuario", description = "• Obtener información del usuario autenticado.")
	public usuario getById(@PathVariable("id") int id) {
		return usuarioService.ObtenerUsuario(id);
	}

	//• Autenticación de usuario. 
	//• Obtener información del usuario autenticado. 
	@PostMapping("/login")
	@Operation(summary = "Autentica a un usuario", description = "• Autenticación de usuario. • Obtener información del usuario autenticado.")
    public usuario Login(@RequestParam String username, @RequestParam String password) {
        if (usuarioService.Login(username, password))
			return usuarioService.ObtenerUsuarioByName(username);
		else
			return null;
    }
}
