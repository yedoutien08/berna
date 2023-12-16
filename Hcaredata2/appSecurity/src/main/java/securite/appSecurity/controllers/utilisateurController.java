package securite.appSecurity.controllers;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import securite.appSecurity.dto.AuthRequest;
import securite.appSecurity.dto.UtilisateurRequest;
import securite.appSecurity.dto.UtilisateurReturn;
import securite.appSecurity.entities.Utilisateur;
import securite.appSecurity.repositories.UtilisateurRepository;
import securite.appSecurity.services.servicesImpl.UtilisateurServiceImpl;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("utilisateur/")
public class utilisateurController {
    private final UtilisateurServiceImpl utilisateurService;
    private final UtilisateurRepository utilisateurRepository;
    @PostMapping("creer")
    public ResponseEntity<Utilisateur> creer(@RequestBody UtilisateurRequest request) throws MessagingException {
        return new ResponseEntity<Utilisateur>(utilisateurService.create(request), HttpStatus.CREATED);
    }
    @PostMapping("connexion")
    public ResponseEntity<UtilisateurReturn> authentication (@RequestBody AuthRequest request){
       return new ResponseEntity<UtilisateurReturn>(utilisateurService.authenticate(request), HttpStatus.OK);
   }

   // @PostMapping("connexion")
   // public ResponseEntity<AuthRequest> authentication (@RequestBody AuthRequest request){
     //   return new ResponseEntity<AuthRequest>(request, HttpStatus.OK);
  //  }

    @PostMapping("resetPassword")
    public ResponseEntity<Utilisateur> resetPassword (@RequestBody AuthRequest request){
        return new ResponseEntity<Utilisateur>(utilisateurService.resetPassword(request), HttpStatus.OK);
    }

    @PostMapping("forgotPassword")
    public ResponseEntity<String> resetPassword (@RequestBody String request) throws MessagingException {
        return new ResponseEntity<String>(utilisateurService.forgotPassword(request), HttpStatus.OK);
    }

    @GetMapping("read/{codeutilisateur}")
    public ResponseEntity<Utilisateur> read(@PathVariable("codeutilisateur") String codeutilisateur){
        return new ResponseEntity<Utilisateur>(utilisateurService.read(codeutilisateur), HttpStatus.OK);
    }
    @PutMapping("update/{codeutilisateur}")
    public ResponseEntity<Utilisateur> update(@PathVariable("codeutilisateur") String codeutilisateur, @RequestBody UtilisateurRequest request){
        return new ResponseEntity<Utilisateur>(utilisateurService.update(codeutilisateur, request), HttpStatus.OK);
    }

    @GetMapping("liste")
    public ResponseEntity<List<Utilisateur>> liste() {
        return new ResponseEntity<List<Utilisateur>>(utilisateurService.liste(), HttpStatus.OK);
    }

    @DeleteMapping("supprimer/{codeutilisateur}")
    public ResponseEntity<Utilisateur> delete(@PathVariable("codeutilisateur") String codeutilisateur){
        return new ResponseEntity<Utilisateur>(utilisateurService.delete(codeutilisateur), HttpStatus.OK);
    }

    @GetMapping("profile")
    public UtilisateurRequest profile(Principal principal) {
        System.out.println(principal);
        Optional<Utilisateur> userFound = utilisateurRepository.findByEmail(principal.getName());
        if (userFound != null && userFound.isPresent()) {
            UtilisateurRequest userModel = new UtilisateurRequest();
            Utilisateur util = userFound.get();
            //userModel.setActif(util.getActif());
           /* Role role = roleRepository.findById(util.getRole().getId()).orElse(null);
            if(role != null) {
                userModel.setRole(role.getRolename());
            }*/
            //userModel.setId(util.getCodeUtilisateur());
            userModel.setNom(util.getNom());
            userModel.setPrenom(util.getPrenom());
            userModel.setEmail(util.getEmail());
            userModel.setProfil(util.getProfil().getCodeProfil());
            userModel.setSexe(util.getSexe());
            // userModel.setDroits(util.getDroits());
            userModel.setPassword(util.getPassword());

            return userModel;
        } else {
            return null;
        }
    }



}
