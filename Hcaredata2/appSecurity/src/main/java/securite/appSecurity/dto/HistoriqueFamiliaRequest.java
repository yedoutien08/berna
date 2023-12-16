package securite.appSecurity.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class HistoriqueFamiliaRequest{
    private int id;
    @NotBlank(message = "les informations des parents obligatoire!!")
    private List<EnfantRequest> famille;
    @NotBlank(message = "code de l'enfant obligatoire!!!")
    private String enfant;

}
