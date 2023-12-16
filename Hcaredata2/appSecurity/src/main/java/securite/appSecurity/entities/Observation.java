package securite.appSecurity.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Observation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String note;
    private String comportement;
    private String developpement;
    private String besoin;
    private int statut;
    private LocalDateTime dateCreation;
    private LocalDateTime dateSuppression;
    @ManyToOne
    private Enfant enfant;
}
