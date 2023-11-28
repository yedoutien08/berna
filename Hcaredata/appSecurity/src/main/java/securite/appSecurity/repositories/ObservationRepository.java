package securite.appSecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import securite.appSecurity.entities.Enfant;
import securite.appSecurity.entities.Observation;

import java.util.List;
import java.util.Optional;

@Repository
public interface ObservationRepository extends JpaRepository<Observation, Integer> {
    Optional<Observation> findByIdAndEnfantAndStatut(int id, Enfant enfant, int statut);
    List<Observation> findAllByEnfantAndStatut(Enfant enfant, int statut);
}
