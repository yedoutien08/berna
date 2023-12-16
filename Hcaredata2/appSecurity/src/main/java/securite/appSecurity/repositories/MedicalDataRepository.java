package securite.appSecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import securite.appSecurity.entities.Enfant;
import securite.appSecurity.entities.MedicalData;

import java.util.Optional;

@Repository
public interface MedicalDataRepository extends JpaRepository<MedicalData,Integer> {
    Optional<MedicalData> findByIdAndStatut(int id, int statut);
    Optional<MedicalData> findByEnfantAndStatut(Enfant enfant, int statut);
    Optional<MedicalData> findByIdAndEnfantAndStatut(int id, Enfant enfant, int statut);
}
