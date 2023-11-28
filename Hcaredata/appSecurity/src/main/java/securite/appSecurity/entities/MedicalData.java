package securite.appSecurity.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MedicalData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String antecedent;
    private String allergie;
    private String traitements;
    private Double poids;
    private Double taille;
    private Date dateVulnerabilite;
    private String vaccins;
    @ManyToOne
    private Enfant enfant;
    private int statut;
    private LocalDateTime dateCreation;
    private LocalDateTime dateSuppression;

}
