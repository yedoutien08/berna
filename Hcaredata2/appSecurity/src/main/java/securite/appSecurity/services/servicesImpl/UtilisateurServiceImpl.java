package securite.appSecurity.services.servicesImpl;

import jakarta.mail.MessagingException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import securite.appSecurity.Exceptions.MyException;
import securite.appSecurity.dto.AuthRequest;
import securite.appSecurity.dto.UtilisateurRequest;
import securite.appSecurity.dto.UtilisateurReturn;
import securite.appSecurity.entities.Droit;
import securite.appSecurity.entities.Profil;
import securite.appSecurity.entities.Utilisateur;
import securite.appSecurity.repositories.DroitRepository;
import securite.appSecurity.repositories.UtilisateurRepository;
import securite.appSecurity.services.EmailSenderService;
import securite.appSecurity.services.JwtService;
import securite.appSecurity.services.ProfilService;
import securite.appSecurity.services.UtilisateurService;
import securite.appSecurity.utils.CodeGenerator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.time.LocalDateTime.now;

@Service
@Transactional
public class UtilisateurServiceImpl implements UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;
    private final ProfilService profilService;
    private final DroitRepository droitRepository;
    private final JwtService jwtService;
    private Utilisateur utilisateur;
    private final EmailSenderService emailSenderService;
    private final PasswordEncoder passwordEncoder;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
    private Profil profil;
    private List<Droit> droitList ;
    private String frontendResetPath = "/lo-newpassword";
    private final AuthenticationManager authenticationManager;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository, ProfilService profilService, DroitRepository droitRepository, JwtService jwtService, EmailSenderService emailSenderService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.utilisateurRepository = utilisateurRepository;
        this.profilService = profilService;
        this.droitRepository = droitRepository;
        this.jwtService = jwtService;
        this.emailSenderService = emailSenderService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    @Transactional
    public Utilisateur create(UtilisateurRequest request) throws MessagingException {
        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findByEmail(request.getEmail());

        utilisateur = new Utilisateur();

        profil = profilService.read(request.getProfil());
        if(utilisateurOptional.isPresent()) throw new MyException("Un utilisateur existe deja avec cet adresse email!!");

       /* if(request.getDroits().size()> 0){
            droitList =  new ArrayList<>();
            request.getDroits().forEach(codeDroit->{
                Optional<Droit> droitOptional = droitRepository.findByCodeDroitAndStatut(codeDroit,1);
                if(droitOptional.isEmpty()) throw new MyException(" Aucun droit avec le code "+codeDroit);
                droitList.add(droitOptional.get());
            });

            utilisateur.setDroits(droitList);
        }*/

        utilisateur.setDateCreation(now());
        utilisateur.setEmail(request.getEmail());
        utilisateur.setNom(request.getNom());
        utilisateur.setPrenom(request.getPrenom());
        utilisateur.setSexe(request.getSexe());
        utilisateur.setStatut(1);
        utilisateur.setPassword(CodeGenerator.password(utilisateur.getNom(), utilisateur.getPrenom(),utilisateur.getDateCreation().getYear()));
        utilisateur.setProfil(profil);
        utilisateur.setEnabled(false);
        utilisateur.setCodeUtilisateur((request.getNom().substring(0,2)+ request.getPrenom().substring(0,2) +
                utilisateur.getDateCreation().getMonth() + sdf.format(utilisateur.getDateCreation().getYear())).substring(0,4));

        emailSenderService.sendEmail(utilisateur.getEmail(),"Bienvenue sur HcareData <br/> voici votre mot de passe : "+utilisateur.getPassword(),"<br/> <a href='"+CodeGenerator.frontendServerUrl+ frontendResetPath+"'> " +
                "Cliquez pour changer votre mot de passe et avoir accès à la plateforme </a>");

        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public Utilisateur read(String codeutilisateur) {
        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findByCodeUtilisateurAndStatut(codeutilisateur, 1);
        if(utilisateurOptional.isEmpty()) throw new MyException("Aucun utilisateur avec le code "+ codeutilisateur);

        return utilisateurOptional.get();
    }

    @Override
    public Utilisateur update(String codeutilisateur, UtilisateurRequest request) {
        utilisateur = read(codeutilisateur);

        profil = profilService.read(request.getProfil());
        if(utilisateur == null) throw new MyException("Aucun utilisateur avec le code "+codeutilisateur);

        if(request.getDroits().size()> 0){
            droitList =  new ArrayList<>();
            request.getDroits().forEach(codeDroit->{
                Optional<Droit> droitOptional = droitRepository.findByCodeDroitAndStatut(codeDroit,1);
                if(droitOptional.isEmpty()) throw new MyException(" Aucun droit avec le code "+codeDroit);
                droitList.add(droitOptional.get());
            });

            utilisateur.setDroits(droitList);
        }

        utilisateur.setDateCreation(now());
        utilisateur.setEmail(request.getEmail());
        utilisateur.setNom(request.getNom());
        utilisateur.setSexe(request.getSexe());
        utilisateur.setPassword(request.getPassword());
        utilisateur.setProfil(profil);
        return utilisateurRepository.save(utilisateur);
    }
    @Override
    public Utilisateur delete(String codeUtilisateur) {
        utilisateur = read(codeUtilisateur);
        utilisateur.setStatut(2);
        utilisateur.setDateSuppression(now());
        return utilisateurRepository.save(utilisateur);
    }
    @Override
    public List<Utilisateur> liste() {
       // return utilisateurRepository.findAllByStatut(1);
        return utilisateurRepository.findAll();

    }

    public UtilisateurReturn authenticate(AuthRequest request) {
        Optional<Utilisateur> userOptional =  utilisateurRepository.findByEmail(request.getEmail());
        System.out.println("email "+request.getEmail());
        if(userOptional.isEmpty()) throw new MyException("email introuvable!!");
        var user = userOptional.get();
        if(!user.isEnabled()) throw new MyException("Votre compte n'est pas actif, verifier vos mail ou contactez l'admin de la plateforme!!");

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        if(user.isEnabled() == false) throw new MyException("Impossible de vous connecter veuillez verifier si votre compte est inactif consulter vos mails!! ");
        var jwtToken = jwtService.generateToken(user);


        var reponse = UtilisateurReturn.builder(
                ).codeUser(user.getCodeUtilisateur())
                .profil(user.getProfil())
                .lastname(user.getNom())
                .firstname(user.getPrenom())
                .email(user.getEmail())
                .statut(user.getStatut())
                .token(jwtToken)
                .build();


        return reponse;
    }

    public Utilisateur resetPassword(AuthRequest request){
        Optional<Utilisateur> userOptional = utilisateurRepository.findByEmail(request.getEmail());
        utilisateur = userOptional.get();
        if(utilisateur.isEnabled() == false) {
            if(userOptional.isEmpty() || utilisateur.getStatut() == 2) throw new MyException("aucun compte avec ces informations!");
            if(!utilisateur.getPassword().equals(request.getOldPassword())) throw new MyException("l'ancien mot de passe fourni est incorrecte!!");
            utilisateur.setPassword(passwordEncoder.encode(request.getNewPassword()));
            utilisateur.setEnabled(true);
        }
        else{
            throw new MyException("votre compte est deja actif!!");
        }


        return utilisateurRepository.save(utilisateur);

    }

    public String forgotPassword(String email) throws MessagingException {
        Optional<Utilisateur> userOptional = utilisateurRepository.findByEmail(email);
        if(userOptional.isEmpty()) throw new MyException("aucun utilisateur avec ce mail");
        if(!userOptional.get().isEnabled()) throw new MyException("votre compte n'est pas encore actif!!");
        utilisateur = userOptional.get();
        utilisateur.setPassword(CodeGenerator.password(utilisateur.getNom(), utilisateur.getPrenom(),utilisateur.getDateCreation().getYear()));

        utilisateurRepository.save(utilisateur);

        emailSenderService.sendEmail(utilisateur.getEmail(),"Bienvenue sur HcareData <br/> voici votre mot de passe : "+utilisateur.getPassword(),"<br/> <a href='"+CodeGenerator.frontendServerUrl+ frontendResetPath+"'> " +
                "Cliquez pour changer votre mot de passe et avoir accès à la plateforme </a>");

        return "un mail vous est envoyé allez verifier!";
    }


}
