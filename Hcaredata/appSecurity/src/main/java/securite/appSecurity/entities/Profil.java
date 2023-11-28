package securite.appSecurity.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profil {
    @Id
    private String codeProfil;
    private String libelleProfil;
    @JsonIgnoreProperties
    @OneToMany
    private Collection<Droit> droits;
    private boolean isUpdated;
    private int statut;
    private LocalDateTime dateCreation;
    private LocalDateTime dateUpdate;
    private LocalDateTime dateSuppression;

}
