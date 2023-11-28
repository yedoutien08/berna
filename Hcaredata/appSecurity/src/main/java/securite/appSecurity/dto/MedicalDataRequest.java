package securite.appSecurity.dto;

import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import securite.appSecurity.entities.Enfant;

import java.util.Date;

@Data
@NoArgsConstructor
public class MedicalDataRequest {
    private int id;
    private String antecedent;
    private String allergie;
    private String traitements;
    private String vaccins;
    private String enfant;
    private Double poids;
    private Double taille;
    private Date dateVulnerabilite;
}
