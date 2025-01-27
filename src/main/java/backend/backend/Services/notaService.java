package backend.backend.Services;

import java.sql.Date;
import java.time.LocalDate;


import org.springframework.stereotype.Service;

import backend.backend.Entities.nota;
import backend.backend.Entities.usuario;
import backend.backend.Entities.DTO.notaDTO;
import backend.backend.Repositories.notaRepository;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor

public class notaService {
    private final notaRepository notaRepository;
    private final usuarioService usuarioService;

    public boolean Crearnota(notaDTO notaDTO) {

        nota nota = new nota();
        nota.setTitulo(notaDTO.getTitulo());
        nota.setDetalle(notaDTO.getDetalle());
        nota.setUsuario(usuarioService.ObtenerUsuario(notaDTO.getUsuarioId()));
        nota.setArchivado(notaDTO.getArchivado());
        nota.setFechaIns(Date.valueOf(LocalDate.now()));
        nota.setFechaMod(Date.valueOf(LocalDate.now()));
        nota.setEtiquetas(notaDTO.getEtiquetas());


        notaRepository.save(nota);
        return true;
    }
    public boolean Modificarnota(notaDTO notaDTO) {
        nota nota = this.Obtenernota(notaDTO.getId());
        nota.setTitulo(notaDTO.getTitulo());
        nota.setDetalle(notaDTO.getDetalle());
        nota.setUsuario(usuarioService.ObtenerUsuario(notaDTO.getUsuarioId()));
        nota.setArchivado(notaDTO.getArchivado());
        nota.setFechaMod(Date.valueOf(LocalDate.now()));
        nota.setEtiquetas(notaDTO.getEtiquetas());
        notaRepository.save(nota);
        return true;
    }
    public boolean Eliminarnota(nota nota) {
        notaRepository.delete(nota);
        return true;
    }
    public nota Obtenernota(int id) {
        return notaRepository.findById(id).get();
    }
    public nota[] Obtenernotas() {
        return notaRepository.findAll().toArray(new nota[0]);
    }   
    public nota[] ObtenernotasByUserId(int usuarioId) {
        usuario usuario = usuarioService.ObtenerUsuario(usuarioId);
        return notaRepository.findByUsuario(usuario);
    }
}