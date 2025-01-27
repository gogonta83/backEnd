package backend.backend.Services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import backend.backend.Entities.usuario;
import backend.backend.Repositories.usuarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class usuarioService {
    private final usuarioRepository usuarioRepository;

    public boolean CrearUsuario(usuario usuario) {
        usuarioRepository.save(usuario);
        return true;
    }
    public boolean ModificarUsuario(usuario usuario) {
        usuarioRepository.save(usuario);
        return true;
    }
    public boolean EliminarUsuario(usuario usuario) {
        usuarioRepository.delete(usuario);
        return true;
    }
    public usuario ObtenerUsuario(int id) {
        return usuarioRepository.findById(id).get();
    }
    public usuario ObtenerUsuarioByName(String name) {
        return usuarioRepository.findByName(name);
    }
    public usuario[] ObtenerUsuarios() {
        
        return usuarioRepository.findAll().toArray(new usuario[0]);
    }
    
    public Boolean Login(String username, String password) {

        Optional<usuario> usuarioOpt = Optional.ofNullable(usuarioRepository.findByName( username));

        if (usuarioOpt.isPresent()) {
            usuario usuario = usuarioOpt.get();

            if (usuario.getPass().equals(password)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
