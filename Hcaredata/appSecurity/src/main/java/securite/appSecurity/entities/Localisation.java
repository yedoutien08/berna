package securite.appSecurity.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Localisation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String lieu;
    private String adresse;
    @ManyToOne
    private Ville ville;
    private Double latitude;
    private Double longitude;
    @OneToOne
    private Enfant enfant;
    private int statut;
    private LocalDateTime dateCreation;
    private LocalDateTime dateSuppression;



}
