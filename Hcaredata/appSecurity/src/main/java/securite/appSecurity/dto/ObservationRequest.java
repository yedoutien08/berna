package securite.appSecurity.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ObservationRequest {
    private int id;
    @NotBlank(message = "note pour l'enfant obligatoire!!")
    private String note;
    @NotBlank(message = "besoin de l'enfant obligatoire!!")
    private String besoin;

    @NotBlank(message = "comportement de l'enfant obligatoire!!")
    private String comportement;

    @NotBlank(message = "développement de l'enfant obligatoire!!")
    private String developpement;
    @NotBlank(message = "code de l'enfant obligatoire!!")
    private String enfant;
}
