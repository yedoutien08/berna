package securite.appSecurity.dto;

import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import securite.appSecurity.entities.Enfant;

@Data
@NoArgsConstructor
public class SchoolDataRequest {
    private int id;
    @NotBlank(message = "nom de l'école obligatoire!!!")
    private String schoolName;
    @NotBlank(message = "Situation géographique de l'école obligatoire!!!")
    private String lieu;
    @NotBlank(message = "niveau de l'enfant obligatoire!!!")
    private String niveau;
    @NotBlank(message = "performance de l'enfant obligatoire!!!")
    private String performance;
    @NotBlank(message = "code de l'enfant obligatoire!!!")
    private String enfant;
}
