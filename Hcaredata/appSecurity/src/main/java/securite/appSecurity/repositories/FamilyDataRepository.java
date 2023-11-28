package securite.appSecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import securite.appSecurity.entities.Enfant;
import securite.appSecurity.entities.FamilyData;


import java.util.List;
import java.util.Optional;

@Repository
public interface FamilyDataRepository extends JpaRepository<FamilyData, Integer> {
    Optional<FamilyData> findByIdAndStatut(int id, int statut);
    Optional<FamilyData> findByEnfantAndIdAndStatut(Enfant enfant, int id,int statut);
    List<FamilyData> findAllByEnfantAndStatut(Enfant enfant, int statut);

}
