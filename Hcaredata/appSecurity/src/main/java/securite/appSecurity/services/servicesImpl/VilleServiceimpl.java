package securite.appSecurity.services.servicesImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import securite.appSecurity.Exceptions.MyException;
import securite.appSecurity.dto.VilleRequest;
import securite.appSecurity.entities.Pays;
import securite.appSecurity.entities.Ville;
import securite.appSecurity.repositories.PaysRepository;

import securite.appSecurity.repositories.VilleRepository;
import securite.appSecurity.services.VilleService;

import java.util.List;
import java.util.Optional;

import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
public class VilleServiceimpl implements VilleService {
    private final VilleRepository villeRepository;
    private final PaysRepository paysRepository;
    private Pays pays;
    private Ville ville;
    @Override
    public Ville creer(VilleRequest request) {
        Optional<Pays> paysOptional = paysRepository.findByIdAndStatut(request.getPays(),1);

        if(paysOptional.isEmpty()) throw new MyException("Aucun Pays avec l'identifiant "+request.getPays());
        pays = paysOptional.get();

        Optional<Ville> villeOptional = villeRepository.findByLibelleAndPays(request.getLibelle(), pays);
        if(villeOptional.isPresent()) {
            ville = villeOptional.get();
            if(ville.getStatut() == 1) throw new MyException("une ville de "+pays.getLibelle()+" a deja ce libellé!");
            else if (ville.getStatut() == 2) {
                ville.setStatut(1);
                ville.setDateCreation(now());
                ville.setDateSuppression(null);
                return villeRepository.save(ville);

            }
        }

        ville = new Ville();
        ville.setPays(pays);
        ville.setLibelle(request.getLibelle());
        ville.setStatut(1);
        ville.setDateCreation(now());

        return villeRepository.save(ville);

    }

    @Override
    public Ville read(int id) {
        Optional<Ville> villeOptional = villeRepository.findByIdAndStatut(id,1);
        if(villeOptional.isEmpty()) throw  new MyException("aucune ville avec l'identifiant "+id);
        return villeOptional.get();
    }

    @Override
    public Ville update(int id, VilleRequest request) {
        Optional<Pays> paysOptional = paysRepository.findByIdAndStatut(request.getPays(),1);
        if(paysOptional.isEmpty()) throw new MyException("Aucun Pays avec l'identifiant "+request.getPays());
        pays = paysOptional.get();

        Optional<Ville> villeOptional = villeRepository.findByIdAndStatut(id, 1);
        if(villeOptional.isEmpty()) throw  new MyException("aucune ville avec l'identifiant "+id);
        ville = villeOptional.get();

        ville.setLibelle(request.getLibelle());
        ville.setPays(pays);

        return villeRepository.save(ville);
    }

    @Override
    public Ville supprimer(int id) {
        ville = read(id);
        ville.setStatut(2);

        return villeRepository.save(ville);
    }

    public List<Ville> AllByPays(int idPays){
        Optional<Pays> paysOptional = paysRepository.findByIdAndStatut(idPays,1);
        if(paysOptional.isEmpty()) throw new MyException("Aucun Pays avec l'identifiant "+idPays);
        pays = paysOptional.get();
        return villeRepository.findAllByPaysAndStatut(pays,1);
    }
}
