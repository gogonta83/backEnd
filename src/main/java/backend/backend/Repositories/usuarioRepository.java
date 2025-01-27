package backend.backend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.backend.Entities.usuario;

public interface usuarioRepository extends JpaRepository<usuario, Integer> {

    usuario findByName(String username);
}
