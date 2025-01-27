package backend.backend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.backend.Entities.nota;
import backend.backend.Entities.usuario;

public interface notaRepository extends JpaRepository<nota, Integer> {

    nota[] findByUsuario(usuario usuario);
}
