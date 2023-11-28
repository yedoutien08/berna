package securite.appSecurity.dto;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;
@Data
public class EnfantRequest {
    @NotBlank(message = "Nom Obligatoire!!!")
    private String nom;
    @NotBlank(message = "prenom Obligatoire!!!")
    private String prenom;
    @NotBlank(message = "Date de naissance Obligatoire!!!")
    private Date dateNaiss;
    @NotBlank(message = "sexe Obligatoire!!!")
    private String sexe;
    private String numIdentifiant;
}
