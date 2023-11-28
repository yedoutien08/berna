package securite.appSecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import securite.appSecurity.entities.Profil;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfilRepository extends JpaRepository<Profil, String> {
    Optional<Profil> findByLibelleProfil(String libelleProfil);
    Optional<Profil> findByCodeProfilAndStatut(String codeProfil, int statut);
    List<Profil> findAllByOrderByDateCreationDesc();
}
