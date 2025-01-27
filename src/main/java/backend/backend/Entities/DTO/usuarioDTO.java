package backend.backend.Entities.DTO;

import lombok.Data;
@Data

public class usuarioDTO {
    private Integer id;
    private String name;
    private String mail;
    private Boolean status;
    private String pass;
}
