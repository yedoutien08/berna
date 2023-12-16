package securite.appSecurity.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurRequest {
    @NotBlank(message = "le nom est obligatoire !!!")
    private String nom;
    @NotBlank(message = "le prénom est obligatoire !!!")
    private String prenom;
    @NotBlank(message = "l'email est obligatoire !!!")
    private String email;
    @NotBlank(message = "le genre est obligatoire !!!")
    private String sexe;
    @NotBlank(message = "le mot de passe est obligatoire !!!")
    private String password;
    @NotBlank(message = "le profil est obligatoire !!!")
    private String profil;
    private List<String> droits;
}
