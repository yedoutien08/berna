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
public class DroitRequest {
    @NotBlank(message = "le libelle du droit est obligatoire !!!")
    private String libelleDroit;
}
