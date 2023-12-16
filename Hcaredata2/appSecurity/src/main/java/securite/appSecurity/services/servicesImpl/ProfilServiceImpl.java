package securite.appSecurity.services.servicesImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import securite.appSecurity.Exceptions.MyException;
import securite.appSecurity.dto.ProfilRequest;
import securite.appSecurity.entities.Droit;
import securite.appSecurity.entities.Profil;
import securite.appSecurity.repositories.DroitRepository;
import securite.appSecurity.repositories.ProfilRepository;
import securite.appSecurity.services.DroitService;
import securite.appSecurity.services.ProfilService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
public class ProfilServiceImpl implements ProfilService {
    private final ProfilRepository profilRepository;
    private final DroitService droitService;
    private List<Droit> droitList;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
    private Profil profil;
    @Override
    public Profil create(ProfilRequest request) {
        Optional<Profil> profilOptional = profilRepository.findByLibelleProfil(request.getLibelleProfil());
        if(profilOptional.isPresent()){
            profil = profilOptional.get();
            if(profil.getStatut() == 1) throw new MyException("le profil "+profil.getLibelleProfil() +" existe deja!!");
            profil.setStatut(1);
            profil.setDateSuppression(null);
            profil.setDateUpdate(now());
            profil.setDateCreation(now());

            profilRepository.save(profil);
        }

        profil = new Profil();
        profil.setLibelleProfil(request.getLibelleProfil());
        profil.setDateCreation(now());
        profil.setCodeProfil((request.getLibelleProfil().substring(0,2) + profil.getDateCreation().getMonth() + sdf.format(profil.getDateCreation().getYear())).substring(0,4));
        droitList = new ArrayList<>();

        request.getDroits().forEach(droit->{
           droitList.add(droitService.read(droit));
        });

        profil.setDroits(droitList);

        profil.setStatut(1);

        return profilRepository.save(profil);
    }

    @Override
    public Profil read(String codeProfil) {
        Optional<Profil> profilOptional = profilRepository.findByCodeProfilAndStatut(codeProfil, 1);
        if(profilOptional.isEmpty()) throw new MyException("Aucun profil avec le code "+codeProfil);
        return profilOptional.get() ;
    }

    @Override
    public Profil update(String codeProfil, ProfilRequest request) {
        profil = read(codeProfil);

        profil.setLibelleProfil(request.getLibelleProfil());
        droitList = new ArrayList<>();

        request.getDroits().forEach(droit->{
            droitList.add(droitService.read(droit));
        });

        profil.setDroits(droitList);
        profil.setUpdated(true);
        profil.setDateUpdate(now());

        return profilRepository.save(profil);
    }

    @Override
    public Profil delete(String codeProfil) {
        profil = read(codeProfil);
        profil.setStatut(2);
        profil.setDateSuppression(now());
        return profilRepository.save(profil) ;
    }
    @Override
    public List<Profil> liste() {
        return profilRepository.findAllByOrderByDateCreationDesc();
    }
}
