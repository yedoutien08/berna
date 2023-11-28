package securite.appSecurity.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Utilisateur implements UserDetails {
    @Id
    private String codeUtilisateur;
    private String nom;
    private String prenom;
    private String sexe;
    private String email;
    private String password;
    @ManyToOne
    private Profil profil;
    @OneToMany
    private List<Droit> droits;
    private boolean isEnabled;
    private boolean isUpdated;
    private int statut;
    private LocalDateTime dateCreation;
    private LocalDateTime dateUpdate;
    private LocalDateTime dateSuppression;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(profil.getLibelleProfil()));
    }
    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}
