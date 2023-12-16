package securite.appSecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import securite.appSecurity.entities.Pays;
import securite.appSecurity.entities.Ville;

import java.util.List;
import java.util.Optional;

@Repository
public interface VilleRepository extends JpaRepository<Ville, Integer> {
    Optional<Ville> findByIdAndStatut(int id, int statut);
    Optional<Ville> findByIdAndPaysAndStatut(int id, Pays pays, int statut);
    Optional <Ville> findByLibelleAndPaysAndStatut(String libelle,Pays pays, int statut);
    Optional <Ville> findByLibelleAndPays(String libelle, Pays pays);
    List<Ville> findAllByPaysAndStatut(Pays pays, int statut);
}
