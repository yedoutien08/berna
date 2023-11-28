package securite.appSecurity.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import securite.appSecurity.dto.ProfilRequest;
import securite.appSecurity.entities.Profil;
import securite.appSecurity.services.servicesImpl.ProfilServiceImpl;

import java.util.List;

@RestController
@RequestMapping("profil/")
@RequiredArgsConstructor
@CrossOrigin
public class ProfilController {
    private final ProfilServiceImpl profilService;
    @PostMapping("creer")
    public ResponseEntity<Profil> create(@RequestBody ProfilRequest request){
        return new ResponseEntity<Profil>(profilService.create(request), HttpStatus.CREATED);
    }
    @GetMapping("read/{codeprofil}")
    public ResponseEntity<Profil> read(@PathVariable("codeprofil") String codeprofil){
        return ResponseEntity.ok(profilService.read(codeprofil));
    }
    @PutMapping("update/{codeprofil}")
    public ResponseEntity<Profil> update(@PathVariable("codeprofil") String codeprofil, @RequestBody ProfilRequest request){
        return ResponseEntity.ok(profilService.update(codeprofil, request));
    }
    @DeleteMapping("delete/{codeprofil}")
    public ResponseEntity<Profil> delete(@PathVariable("codeprofil") String codeprofil){
        return ResponseEntity.ok(profilService.delete(codeprofil));
    }
    @GetMapping("liste")
    public ResponseEntity<List<Profil>> liste(){
        return ResponseEntity.ok(profilService.liste());
    }
}
