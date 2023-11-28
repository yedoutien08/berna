package securite.appSecurity.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PostPersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Droit implements Serializable {
    @Id
    private String codeDroit;
    private String libelleDroit;
    private Boolean isUpdated;
    private Integer statut;
    private LocalDateTime dateCreation;
    private LocalDateTime dateUpdate;
    private LocalDateTime dateSuppression;




}
