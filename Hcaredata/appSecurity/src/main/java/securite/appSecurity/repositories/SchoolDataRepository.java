package securite.appSecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import securite.appSecurity.entities.Enfant;
import securite.appSecurity.entities.SchoolData;

import java.util.Optional;

@Repository
public interface SchoolDataRepository extends JpaRepository<SchoolData, Integer> {
    Optional<SchoolData> findByIdAndStatut(int id, int statut);
    Optional<SchoolData> findByEnfantAndStatut(Enfant enfant, int statut);
}
