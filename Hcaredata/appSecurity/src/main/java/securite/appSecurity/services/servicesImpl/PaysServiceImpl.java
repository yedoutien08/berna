package securite.appSecurity.services.servicesImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import securite.appSecurity.Exceptions.MyException;
import securite.appSecurity.dto.PaysRequest;
import securite.appSecurity.entities.Pays;
import securite.appSecurity.repositories.PaysRepository;
import securite.appSecurity.services.PaysService;

import java.util.List;
import java.util.Optional;

import static java.time.LocalDateTime.now;

@Service
public class PaysServiceImpl implements PaysService {
    @Autowired
    PaysRepository paysRepository;


    private Pays pays;

    public Pays creer(PaysRequest request) {
        Optional<Pays> paysOptional = paysRepository.findByLibelle(request.getLibelle());
        if(paysOptional.isPresent()){
            pays = paysOptional.get();
            if(pays.getStatut()== 1) throw new MyException("un pays existe deja avec le libellé "+request.getLibelle());
            else if (pays.getStatut() == 2) {
                pays.setStatut(1);
                pays.setDateCreation(now());
                pays.setDateSuppression(null);

                return paysRepository.save(pays);
            }
        }
        pays = new Pays();
        pays.setLibelle(request.getLibelle());
        pays.setStatut(1);
        pays.setDateCreation(now());
        return paysRepository.save(pays);
    }


    public Pays read(int id) {
        Optional<Pays> paysOptional = paysRepository.findByIdAndStatut(id,1);
        if(paysOptional.isEmpty()) throw new MyException("aucun pays avec l'identifiant "+id);
        return paysOptional.get();
    }


    public Pays update(int id, PaysRequest request) {
        pays = read(id);

        pays.setLibelle(request.getLibelle());
        return paysRepository.save(pays);
    }


    public Pays supprimer(int id) {
        pays = read(id);
        pays.setStatut(2);
        return paysRepository.save(pays);
    }

    public List<Pays> getAll(){
        return paysRepository.findAllByStatut(1);
    }
}
