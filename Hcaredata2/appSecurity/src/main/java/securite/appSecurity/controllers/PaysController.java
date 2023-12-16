package securite.appSecurity.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import securite.appSecurity.dto.PaysRequest;
import securite.appSecurity.entities.Pays;
import securite.appSecurity.services.PaysService;
import securite.appSecurity.services.servicesImpl.PaysServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("pays/")
public class PaysController {
    private final PaysService paysService;

    @PostMapping("creer")
    public ResponseEntity<Pays> create(@RequestBody @Valid PaysRequest request){
        return new ResponseEntity<Pays>(paysService.creer(request), HttpStatus.CREATED);
    }

    @GetMapping("read/{id}")
    public ResponseEntity<Pays> read(@PathVariable("id") int id){
        return ResponseEntity.ok(paysService.read(id));
    }
    @GetMapping("list")
    public ResponseEntity<List<Pays>> getAll(){
        return ResponseEntity.ok(paysService.getAll());
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Pays> update(@PathVariable("id") int id, @RequestBody @Valid PaysRequest request){
        return ResponseEntity.ok(paysService.update(id,request));
    }
    @DeleteMapping("supprimer/{id}")
    public ResponseEntity<Pays> supprimer(@PathVariable("id") int id){
        return ResponseEntity.ok(paysService.supprimer(id));
    }


}
