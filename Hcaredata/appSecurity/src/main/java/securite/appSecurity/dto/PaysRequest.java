package securite.appSecurity.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaysRequest {

    private int id;
    @NotBlank(message = "Libelle Obligatoire")
    private String libelle;
}
