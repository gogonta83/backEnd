package backend.backend.Entities;

import java.sql.Date;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic
    @Column(nullable=false)
    private String titulo;
    @Column(nullable=false)
    private String etiquetas;
    @Column(nullable=false)
    private String detalle;
    @Column(nullable=false)
    
    private Date fechaIns;
    @Column(nullable=false)
    
    private Date fechaMod;
    @Column(name="archivado" ,nullable=false)
    private Boolean archivado;

    @ManyToOne 
    @JoinColumn(name = "usuarioId", nullable = false) 
    private usuario usuario;


}
