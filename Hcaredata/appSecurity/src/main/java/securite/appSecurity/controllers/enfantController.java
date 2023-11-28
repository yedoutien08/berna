package securite.appSecurity.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import securite.appSecurity.dto.CollectData;
import securite.appSecurity.entities.Enfant;
import securite.appSecurity.services.servicesImpl.EnfantServiceImpl;

@RestController
@RequestMapping("collectData/")
@RequiredArgsConstructor
public class enfantController {
    private final EnfantServiceImpl enfantService;
    @PostMapping("create")
    public ResponseEntity<Enfant> collectRegister(@RequestBody CollectData request){
        return new ResponseEntity<Enfant>(enfantService.creer(request), HttpStatus.CREATED);
    }

}
