package securite.appSecurity.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import securite.appSecurity.dto.VilleRequest;
import securite.appSecurity.entities.Ville;
import securite.appSecurity.services.servicesImpl.VilleServiceimpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("ville/")
public class VilleController {
    private final VilleServiceimpl villeService;

    @PostMapping("creer")
    public ResponseEntity<Ville> creer(@RequestBody @Valid VilleRequest request){
        return new  ResponseEntity<Ville>(villeService.creer(request), HttpStatus.CREATED);
    }

    @GetMapping("read/{id}")
    public ResponseEntity<Ville> read(@PathVariable("id") int id){
        return ResponseEntity.ok(villeService.read(id));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Ville> update(@PathVariable("id") int id, @RequestBody @Valid VilleRequest request){
        return ResponseEntity.ok(villeService.update(id, request));
    }

    @DeleteMapping("supprimer/{id}")
    public ResponseEntity<Ville> delete(@PathVariable("id") int id){
        return ResponseEntity.ok(villeService.supprimer(id));
    }
    @GetMapping("allByPays/{idPays}")
    public ResponseEntity<List<Ville>> getAllByPays(@PathVariable("idPays") int idPays){
        return ResponseEntity.ok(villeService.AllByPays(idPays));
    }


}
