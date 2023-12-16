package securite.appSecurity.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collection;

//@Data
//@Entity
//@NoArgsConstructor
//@AllArgsConstructor
public class HistoriqueFamilial {
   // @Id
  //  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany
    private Collection<Enfant> parentsEtFreres;
    @OneToOne
    private Enfant enfant;
    private int statut;
    private LocalDateTime dateCreation;
    private LocalDateTime dateSuppression;

}
