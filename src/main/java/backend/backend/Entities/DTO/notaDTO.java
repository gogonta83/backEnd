package backend.backend.Entities.DTO;

import java.sql.Date;


import lombok.Data;

@Data

public class notaDTO {

    private Integer id;
    private String titulo;
    private String etiquetas;
    private String detalle;
    private Date fechaIns;
    private Date fechaMod;
    private Boolean archivado;
    private int usuarioId;
}
