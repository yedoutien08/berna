package securite.appSecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import securite.appSecurity.entities.Enfant;
import securite.appSecurity.entities.Photo;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhototRepository extends JpaRepository<Photo, Integer> {
    Optional<Photo> findByIdAndStatut(int id, int statut);
    Optional<Photo> findByEnfantAndIdAndStatut(Enfant enfant, int id, int statut);
    List<Photo> findAllByEnfantAndStatutOrderByDateCreation(Enfant enfant, int statut);
    List<Photo> findAllByEnfantAndStatut(Enfant enfant, int statut);
}
