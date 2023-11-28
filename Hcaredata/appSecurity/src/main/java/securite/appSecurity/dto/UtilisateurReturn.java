package securite.appSecurity.dto;

import lombok.Builder;
import lombok.Data;
import securite.appSecurity.entities.Profil;

@Data
@Builder
public class UtilisateurReturn {
    private String codeUser;
    private String lastname;
    private String firstname;
    private String email;
    private Profil profil;
    private int statut;
    private  String token;
}
