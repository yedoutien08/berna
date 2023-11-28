package securite.appSecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import securite.appSecurity.entities.Droit;

import java.util.List;
import java.util.Optional;

@Repository
public interface DroitRepository extends JpaRepository<Droit, String> {
    Optional<Droit> findByLibelleDroit(String libelleDroit);
    Optional<Droit> findByCodeDroitAndStatut(String codeDroit, int statut);
    List<Droit> findAllByOrderByDateCreationDesc();
}
