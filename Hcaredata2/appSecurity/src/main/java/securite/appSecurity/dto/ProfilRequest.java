package securite.appSecurity.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import securite.appSecurity.entities.Droit;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfilRequest {
    @NotBlank(message = "le libelle du profil est obligatoire !!!")
    private String libelleProfil;
    @NotBlank(message = "la liste des droits du profil est obligatoire !!!")
    private List<String> droits;
}
