package securite.appSecurity.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
public class FamilyDataRequest {
    private int id;
    @NotBlank(message = "Nom Obligatoire!!!")
    private String nom;
    @NotBlank(message = "prenom Obligatoire!!!")
    private String prenom;
    @NotBlank(message = "age Obligatoire!!!")
    private int age;
    @NotBlank(message = "sexe Obligatoire!!!")
    private String sexe;
    @NotBlank(message = "profession Obligatoire!!!")
    private String profession;
    @NotBlank(message = "categorie Obligatoire!!!")
    private String categorie;
}
