package securite.appSecurity.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import securite.appSecurity.dto.DroitRequest;
import securite.appSecurity.entities.Droit;

import securite.appSecurity.services.servicesImpl.DroitServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("droit/")
public class DroitController {
    private final DroitServiceImpl droitService;
    @PostMapping("creer")
    public ResponseEntity<Droit> create(@Valid @RequestBody DroitRequest request){
        return new ResponseEntity<Droit>(droitService.create(request), HttpStatus.CREATED);
    }
    @GetMapping("lire/{id}")
    public ResponseEntity<Droit> read(@PathVariable("id") String id){
        return ResponseEntity.ok(droitService.read(id));
    }
    @GetMapping("liste")
    public ResponseEntity<List<Droit>> all(){
        return ResponseEntity.ok(droitService.getAllDroit());
    }
    @PutMapping("modifier/{id}")
    public ResponseEntity<Droit> update(@PathVariable("id")String id, @Valid @RequestBody DroitRequest request){
        return ResponseEntity.ok(droitService.update(id,request));
    }
    @DeleteMapping("supprimer/{id}")
    public ResponseEntity<Droit> delete(@PathVariable("id") String id){
        return ResponseEntity.ok(droitService.delete(id));
    }
}
