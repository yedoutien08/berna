package securite.appSecurity.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FamilyData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String prenom;
    private String profession;
    private int age;
    private String sexe;
    @ManyToOne
    private Enfant enfant;
    private int statut;
    private String categorie;
    private LocalDateTime dateCreation;
    private LocalDateTime dateSuppression;
}
