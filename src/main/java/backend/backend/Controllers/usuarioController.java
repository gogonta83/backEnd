package backend.backend.Controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import backend.backend.Entities.usuario;
import backend.backend.Services.usuarioService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    // • Registro de nuevos usuarios.
    @PostMapping
    @Operation(summary = "Crear un usuario", description = "• Registro de nuevos usuarios.")
    public ResponseEntity<String> CrearUsuario(@RequestBody @Validated usuario usuario) {
        usuarioService.CrearUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado exitosamente");
    }

    @PutMapping
    @Operation(summary = "Modificar un usuario", description = "• Modificación de un usuario existente.")
    public ResponseEntity<String> ModificarUsuario(@RequestBody @Validated usuario usuario) {
        boolean resultado = usuarioService.ModificarUsuario(usuario);
        if (resultado) {
            return ResponseEntity.ok("Usuario modificado exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un usuario", description = "• Eliminación de un usuario por ID.")
    public ResponseEntity<String> EliminarUsuario(@PathVariable("id") int id) {
        if (id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID inválido");
        }

        var usuario = usuarioService.ObtenerUsuario(id);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }

        boolean resultado = usuarioService.EliminarUsuario(usuario);
        if (resultado) {
            return ResponseEntity.ok("Usuario eliminado exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el usuario");
        }
    }

    @GetMapping
    @Operation(summary = "Obtener todos los usuarios", description = "• Obtención de todos los usuarios.")
    public ResponseEntity<usuario[]> GetAll() {
        usuario[] usuarios = usuarioService.ObtenerUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene a un usuario", description = "• Obtener información del usuario autenticado.")
    public usuario GetById(@PathVariable("id") int id) {

        if (id <= 0)
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "invalid id");

        return usuarioService.ObtenerUsuario(id);
    }

    // • Autenticación de usuario.
    // • Obtener información del usuario autenticado.
    @PostMapping("/login")
    @Operation(summary = "Autentica a un usuario", description = "• Autenticación de usuario. • Obtener información del usuario autenticado.")
    public usuario Login(@RequestParam String username, @RequestParam String password) {

        if (username == "" || password == "")
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "invalid data");

        if (usuarioService.Login(username, password))
            return usuarioService.ObtenerUsuarioByName(username);
        else
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "invalid data");
    }
}
