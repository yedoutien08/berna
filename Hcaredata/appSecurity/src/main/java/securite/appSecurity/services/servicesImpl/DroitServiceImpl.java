package securite.appSecurity.services.servicesImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import securite.appSecurity.Exceptions.MyException;
import securite.appSecurity.dto.DroitRequest;
import securite.appSecurity.entities.Droit;
import securite.appSecurity.repositories.DroitRepository;
import securite.appSecurity.services.DroitService;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
public class DroitServiceImpl implements DroitService {
    private final DroitRepository droitRepository;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
    //private Droit droit = new Droit();
    @Override
    public Droit create(DroitRequest request) {
        Optional<Droit> droitOptional = droitRepository.findByLibelleDroit(request.getLibelleDroit());
        if(droitOptional.isPresent()){
            Droit droit = droitOptional.get();
            if(droit.getStatut() == 1) throw  new MyException("ce droit existe deja !!");
            if(droit.getStatut() == 2){
                droit.setStatut(2);
                droit.setDateUpdate(now());
                droit.setDateSuppression(null);
                droitRepository.save(droit);
            }
        }

        Droit droit = new Droit();
        droit.setDateCreation(now());
        droit.setLibelleDroit(request.getLibelleDroit());
        droit.setIsUpdated(Boolean.FALSE);
        droit.setCodeDroit((request.getLibelleDroit().substring(0,2) + droit.getDateCreation().getMonth() + sdf.format(droit.getDateCreation().getYear())).substring(0,4));
        droit.setStatut(1);

        return droitRepository.save(droit);

    }

    @Override
    public Droit read(String codeDroit) {
        Optional<Droit> droitOptional = droitRepository.findByCodeDroitAndStatut(codeDroit, 1);
        if(droitOptional.isEmpty()) throw new MyException("Aucun droit avec l'identifiant "+ codeDroit);

        return droitOptional.get();
    }

    @Override
    public Droit update(String codeDroit, DroitRequest request) {
        Optional<Droit> droitOptional = droitRepository.findByCodeDroitAndStatut(codeDroit, 1);
        if(droitOptional.isEmpty()) throw new MyException("Aucun droit avec l'identifiant "+ codeDroit);
        Droit droit = droitOptional.get();

        droit.setLibelleDroit(request.getLibelleDroit());
        droit.setIsUpdated(Boolean.TRUE);
        droit.setDateUpdate(now());
        return droitRepository.save(droit);
    }

    @Override
    public Droit delete(String codeDroit) {
        Optional<Droit> droitOptional = droitRepository.findByCodeDroitAndStatut(codeDroit, 1);
        if(droitOptional.isEmpty()) throw new MyException("Aucun droit avec l'identifiant "+ codeDroit);
        Droit droit = droitOptional.get();
        droit.setDateSuppression(now());
        droit.setStatut(2);
        return droitRepository.save(droit);
    }

    @Override
    public List<Droit> getAllDroit() {
        return droitRepository.findAllByOrderByDateCreationDesc();
    }
}
