package securite.appSecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import securite.appSecurity.entities.Pays;

import java.util.List;
import java.util.Optional;

public interface PaysRepository extends JpaRepository<Pays, Integer> {
    Optional<Pays> findByIdAndStatut(int id, int statut);
    Optional <Pays> findByLibelleAndStatut(String libelle, int statut);
    Optional <Pays> findByLibelle(String libelle);
    List<Pays> findAllByStatut(int statut);
}
