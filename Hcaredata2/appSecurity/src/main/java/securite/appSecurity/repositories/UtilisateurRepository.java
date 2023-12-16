package securite.appSecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import securite.appSecurity.entities.Utilisateur;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, String> {
    Optional<Utilisateur> findByEmail(String email);
    Optional<Utilisateur> findByCodeUtilisateurAndStatut(String codeutilisateur, int statut);

    List<Utilisateur> findAllByStatut(int statut);
}
