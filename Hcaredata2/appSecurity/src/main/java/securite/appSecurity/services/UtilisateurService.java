package securite.appSecurity.services;

import jakarta.mail.MessagingException;
import securite.appSecurity.dto.AuthRequest;
import securite.appSecurity.dto.UtilisateurRequest;
import securite.appSecurity.dto.UtilisateurReturn;
import securite.appSecurity.entities.Utilisateur;

import java.util.List;

public interface UtilisateurService {
    Utilisateur create(UtilisateurRequest request) throws MessagingException;
    Utilisateur read(String codeutilisateur);
    Utilisateur update(String codeutilisateur, UtilisateurRequest request);
    Utilisateur delete(String codeUtilisateur);
    List<Utilisateur> liste();
    UtilisateurReturn authenticate(AuthRequest request);
    Utilisateur resetPassword(AuthRequest request);
    String forgotPassword(String email) throws MessagingException;


}
