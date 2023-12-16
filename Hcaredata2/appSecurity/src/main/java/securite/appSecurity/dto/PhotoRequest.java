package securite.appSecurity.dto;

import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import securite.appSecurity.entities.Enfant;

@Data
@NoArgsConstructor
public class PhotoRequest {
    private int id;
    @NotBlank(message = "image obligatoire!!")
    private String image;
    @NotBlank(message = "code de l'enfant obligatoire!!")
    private String enfant;
}
