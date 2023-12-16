package securite.appSecurity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VilleRequest {
    @NotBlank(message = "Libelle Obligatoire")
    private String libelle;
    @NotNull(message = "Pays Obligatoire")
    private int pays;
}
