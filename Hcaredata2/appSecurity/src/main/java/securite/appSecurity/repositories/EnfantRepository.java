package securite.appSecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import securite.appSecurity.entities.Enfant;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnfantRepository extends JpaRepository<Enfant, Integer> {
    Optional<Enfant> findByIdAndStatut(int id, int statut);
   /* Optional<Enfant> findByCodeEnfantAndStatut(String codeEnfant, int statut);*/
    Optional<Enfant> findByNomAndPrenomAndSexe(String nom, String prenom, String sexe);
    List<Enfant> findAllByStatutOrderByDateCreation(int statut);
}
