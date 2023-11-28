package securite.appSecurity.dto;

import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import securite.appSecurity.entities.Enfant;

@Data
@NoArgsConstructor
public class LocalisationRequest {
    private int id;
    @NotBlank(message = "adresse obligatoire!!!")
    private String adresse;

    @NotBlank(message = "lieu obligatoire!!!")
    private String lieu;
    @NotBlank(message = "ville obligatoire!!!")
    private int ville;
    @NotBlank(message = "pays obligatoire!!!")
    private int pays;
    @NotBlank(message = "latitude obligatoire!!!")
    private Double latitude;
    @NotBlank(message = "longitude obligatoire!!!")
    private Double longitude;
    @NotBlank(message = "code enfant obligatoire!!!")
    private String enfant;
}
