package securite.appSecurity.services.servicesImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import securite.appSecurity.Exceptions.MyException;
import securite.appSecurity.dto.CollectData;
import securite.appSecurity.entities.*;
import securite.appSecurity.repositories.*;
import securite.appSecurity.services.EnfantService;
import securite.appSecurity.utils.CodeGenerator;

import java.util.List;
import java.util.Optional;

import static java.time.LocalDateTime.now;
@Service
@RequiredArgsConstructor
public class EnfantServiceImpl implements EnfantService {
    private final EnfantRepository enfantRepository;
    private final PhototRepository phototRepository;
    private final LocalisationRepository localisationRepository;
    private final MedicalDataRepository medicalDataRepository;
    private final SchoolDataRepository schoolDataRepository;
    private final PaysServiceImpl paysService;
    private final FamilyDataRepository familyDataRepository;
    private final ObservationRepository observationRepository;
    private final VilleRepository villeRepository;
    private Photo photo;
    private Enfant enfant;
    private Pays pays;
    private Ville ville;

    @Override
    @Transactional
    public Enfant creer(CollectData request) {
        Optional<Enfant>optionalEnfant = enfantRepository.findByNomAndPrenomAndSexe(request.getEnfant().getNom(), request.getEnfant().getPrenom(),request.getEnfant().getSexe());
        if(optionalEnfant.isPresent()){
            enfant = optionalEnfant.get();
            if(enfant.getStatut() == 1) throw new MyException("cet enfant existe deja!!!");

            enfant.setStatut(1);
             enfant = enfantRepository.save(enfant);

        }

        enfant = new Enfant();
        enfant.setNom(request.getEnfant().getNom());
        enfant.setPrenom(request.getEnfant().getPrenom());
        enfant.setSexe(request.getEnfant().getSexe());
        enfant.setDateNaiss(request.getEnfant().getDateNaiss());
        enfant.setNumIdentifiant(CodeGenerator.numIdentifiant(request.getEnfant().getNom(),request.getEnfant().getPrenom(),request.getEnfant().getDateNaiss().getYear()));
        enfant.setDateCreation(now());
        enfant.setStatut(1);

        enfant = enfantRepository.save(enfant);


       /* request.getPhotos().forEach(tof->{
            photo = new Photo();
            photo.setImage(tof.getImage());
            photo.setEnfant(enfant);
            photo.setStatut(1);
            photo.setDateCreation(now());
            phototRepository.save(photo);
        }); */

       var localisation = new Localisation();

        pays = paysService.read(request.getLocalisation().getPays());
        Optional<Ville> villeOptional = villeRepository.findByIdAndPaysAndStatut(request.getLocalisation().getVille(), pays, 1);

      if(villeOptional.isEmpty()) throw new MyException("aucune ville de "+pays.getLibelle()+" avec l'id "+ request.getLocalisation().getVille());
       ville = villeOptional.get();
       localisation.setEnfant(enfant);
       localisation.setLieu(request.getLocalisation().getLieu());
       localisation.setVille(ville);
       localisation.setLongitude(request.getLocalisation().getLongitude());
       localisation.setLatitude(request.getLocalisation().getLatitude());
       localisation.setStatut(1);
       localisation.setDateCreation(now());

       localisationRepository.save(localisation);

       var schoolData = new SchoolData();

       schoolData.setEnfant(enfant);
       schoolData.setLieu(request.getSchoolData().getLieu());
       schoolData.setNiveau(request.getSchoolData().getNiveau());
       schoolData.setPerformance(request.getSchoolData().getPerformance());
       schoolData.setSchoolName(request.getSchoolData().getSchoolName());
       schoolData.setStatut(1);
       schoolData.setDateCreation(now());

       schoolDataRepository.save(schoolData);



       request.getFamilyDataRequests().forEach(
               data->{
                   var familyData = new FamilyData();

                   familyData.setEnfant(enfant);
                   familyData.setNom(data.getNom());
                   familyData.setSexe(data.getSexe());
                   familyData.setPrenom(data.getPrenom());
                   familyData.setCategorie(data.getCategorie());
                   familyData.setAge(data.getAge());
                   familyData.setStatut(1);
                   familyData.setProfession(data.getProfession());
                   familyData.setDateCreation(now());

                   familyDataRepository.save(familyData);
               }
       );

       request.getObservations().forEach(obs->{
           var observation = new Observation();

           observation.setEnfant(enfant);
           observation.setStatut(1);
           observation.setBesoin(obs.getBesoin());
           observation.setComportement(obs.getComportement());
           observation.setDeveloppement(obs.getDeveloppement());
           observation.setDateCreation(now());

           observationRepository.save(observation);
       });

       var medicalData = new MedicalData();

       medicalData.setEnfant(enfant);
       medicalData.setStatut(1);
       medicalData.setTaille(request.getMedaicalData().getTaille());
       medicalData.setAllergie(request.getMedaicalData().getAllergie());
       medicalData.setAntecedent(request.getMedaicalData().getAntecedent());
       medicalData.setPoids(request.getMedaicalData().getPoids());
       medicalData.setVaccins(request.getMedaicalData().getVaccins());
       medicalData.setTraitements(request.getMedaicalData().getTraitements());
       medicalData.setDateVulnerabilite(request.getMedaicalData().getDateVulnerabilite());
       medicalData.setDateCreation(now());

       medicalDataRepository.save(medicalData);

        return enfant;
    }
    @Override
    @Transactional
    public Enfant modifier(int id,CollectData request) {
        Optional<Enfant> enfantOptional = enfantRepository.findByIdAndStatut(id,1);
        if(enfantOptional.isEmpty()) throw new MyException("aucun enfant avec l'identifiant "+id);
        enfant = enfantOptional.get();

        enfant.setNom(request.getEnfant().getNom());
        enfant.setPrenom(request.getEnfant().getPrenom());
        enfant.setSexe(request.getEnfant().getSexe());
        enfant.setDateNaiss(request.getEnfant().getDateNaiss());
        enfant.setDateModif(now());

        enfantRepository.save(enfant);

        var localisation = new Localisation();

        Optional<Localisation> localisationOptional = localisationRepository.findByIdAndEnfantAndStatut(request.getLocalisation().getId(), enfant,1);

        if(localisationOptional.isPresent()){
            localisation = localisationOptional.get();

            pays = paysService.read(request.getLocalisation().getPays());
            Optional<Ville> villeOptional = villeRepository.findByIdAndPaysAndStatut(request.getLocalisation().getVille(), pays, 1);

            if(villeOptional.isEmpty()) throw new MyException("aucune ville de "+pays.getLibelle()+" avec l'id "+ request.getLocalisation().getVille());
            ville = villeOptional.get();
            localisation.setEnfant(enfant);
            localisation.setLieu(request.getLocalisation().getLieu());
            localisation.setVille(ville);
            localisation.setLongitude(request.getLocalisation().getLongitude());
            localisation.setLatitude(request.getLocalisation().getLatitude());
            localisation.setStatut(1);
            localisation.setDateCreation(now());

        }
        else{
            pays = paysService.read(request.getLocalisation().getPays());
            Optional<Ville> villeOptional = villeRepository.findByIdAndPaysAndStatut(request.getLocalisation().getVille(), pays, 1);

            if(villeOptional.isEmpty()) throw new MyException("aucune ville de "+pays.getLibelle()+" avec l'id "+ request.getLocalisation().getVille());
            ville = villeOptional.get();
            localisation.setEnfant(enfant);
            localisation.setLieu(request.getLocalisation().getLieu());
            localisation.setVille(ville);
            localisation.setLongitude(request.getLocalisation().getLongitude());
            localisation.setLatitude(request.getLocalisation().getLatitude());
            localisation.setStatut(1);
            localisation.setDateCreation(now());
        }

        localisationRepository.save(localisation);


        var schoolData = new SchoolData();



        schoolData.setEnfant(enfant);
        schoolData.setLieu(request.getSchoolData().getLieu());
        schoolData.setNiveau(request.getSchoolData().getNiveau());
        schoolData.setPerformance(request.getSchoolData().getPerformance());
        schoolData.setSchoolName(request.getSchoolData().getSchoolName());
        schoolData.setStatut(1);
        schoolData.setDateCreation(now());

        schoolDataRepository.save(schoolData);



        request.getFamilyDataRequests().forEach(
                data->{
                    var familyData = new FamilyData();

                    familyData.setEnfant(enfant);
                    familyData.setNom(data.getNom());
                    familyData.setSexe(data.getSexe());
                    familyData.setPrenom(data.getPrenom());
                    familyData.setCategorie(data.getCategorie());
                    familyData.setAge(data.getAge());
                    familyData.setStatut(1);
                    familyData.setProfession(data.getProfession());
                    familyData.setDateCreation(now());

                    familyDataRepository.save(familyData);
                }
        );

        request.getObservations().forEach(obs->{
            var observation = new Observation();

            observation.setEnfant(enfant);
            observation.setStatut(1);
            observation.setBesoin(obs.getBesoin());
            observation.setComportement(obs.getComportement());
            observation.setDeveloppement(obs.getDeveloppement());
            observation.setDateCreation(now());

            observationRepository.save(observation);
        });

        var medicalData = new MedicalData();

        medicalData.setEnfant(enfant);
        medicalData.setStatut(1);
        medicalData.setTaille(request.getMedaicalData().getTaille());
        medicalData.setAllergie(request.getMedaicalData().getAllergie());
        medicalData.setAntecedent(request.getMedaicalData().getAntecedent());
        medicalData.setPoids(request.getMedaicalData().getPoids());
        medicalData.setVaccins(request.getMedaicalData().getVaccins());
        medicalData.setTraitements(request.getMedaicalData().getTraitements());
        medicalData.setDateVulnerabilite(request.getMedaicalData().getDateVulnerabilite());
        medicalData.setDateCreation(now());

        medicalDataRepository.save(medicalData);




        return null;
    }

    @Override
    public Enfant lire(int id) {
        return null;
    }

    @Override
    public Enfant supprimer(int id) {
        return null;
    }

    @Override
    public List<Enfant> liste() {
        return null;
    }
}
