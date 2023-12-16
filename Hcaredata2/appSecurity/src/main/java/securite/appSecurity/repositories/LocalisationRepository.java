package securite.appSecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import securite.appSecurity.entities.Enfant;
import securite.appSecurity.entities.Localisation;

import java.util.Optional;

@Repository
public interface LocalisationRepository extends JpaRepository<Localisation, Integer> {
    Optional<Localisation> findByIdAndStatut(int id, int statut);
    Optional<Localisation> findByEnfantAndStatut(Enfant enfant, int statut);
    Optional<Localisation> findByIdAndEnfantAndStatut(int id, Enfant enfant, int statut);
}
