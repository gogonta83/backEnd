package backend.backend.Services;

import org.springframework.stereotype.Service;

import backend.backend.Entities.etiqueta;
import backend.backend.Entities.usuario;
import backend.backend.Entities.DTO.etiquetaDTO;
import backend.backend.Repositories.etiquetaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class etiquetaService {
    private final etiquetaRepository etiquetaRepository;
    private final usuarioService usuarioService;
    public boolean Crearetiqueta(etiquetaDTO etiquetaDTO) {

        var etiqueta = new etiqueta();
        etiqueta.setNombre(etiquetaDTO.getNombre());
        etiqueta.setUsuario(usuarioService.ObtenerUsuario(etiquetaDTO.getUsuarioId()));

        etiquetaRepository.save(etiqueta);
        return true;
    }
    public boolean Modificaretiqueta(etiquetaDTO etiquetaDTO) {

        var etiqueta = new etiqueta();
        etiqueta.setNombre(etiquetaDTO.getNombre());
        etiqueta.setUsuario(usuarioService.ObtenerUsuario(etiquetaDTO.getUsuarioId()));

        etiquetaRepository.save(etiqueta);
        return true;
    }
    public boolean Eliminaretiqueta(etiqueta etiqueta) {
        etiquetaRepository.delete(etiqueta);
        return true;
    }
    public etiqueta Obteneretiqueta(int id) {
        return etiquetaRepository.findById(id).get();
    }
    public etiqueta[] Obteneretiquetas() {
        return etiquetaRepository.findAll().toArray(new etiqueta[0]);
    }   
    public etiqueta[] ObteneretiquetasByUserId(int usuarioId) {
        usuario usuario = usuarioService.ObtenerUsuario(usuarioId);
        if (usuario == null)
            return null;

        return etiquetaRepository.findByUsuario(usuario);
    }
}
