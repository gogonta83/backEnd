package backend.backend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.backend.Entities.etiqueta;
import backend.backend.Entities.usuario;

public interface etiquetaRepository extends JpaRepository<etiqueta, Integer> {
    etiqueta[] findByUsuario(usuario usuario);  
}
