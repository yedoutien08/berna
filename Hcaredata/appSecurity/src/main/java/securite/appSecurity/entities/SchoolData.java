package securite.appSecurity.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class SchoolData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String schoolName;
    private String lieu;
    private String niveau;
    private String performance;
    @OneToOne
    private Enfant enfant;
    private int statut;
    private LocalDateTime dateCreation;
    private LocalDateTime dateSuppression;


}
