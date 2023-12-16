package securite.appSecurity.controllers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import securite.appSecurity.dto.CollectData;
import securite.appSecurity.entities.Enfant;
import securite.appSecurity.services.servicesImpl.EnfantServiceImpl;

import java.util.List;

@RestController
@RequestMapping("collectData/")
@RequiredArgsConstructor
public class enfantController {
    private final EnfantServiceImpl enfantService;
    @PostMapping("create")
    public ResponseEntity<Enfant> collectRegister(@NonNull @RequestPart("collectData") CollectData collectData,
                                                  @RequestPart(value = "photos", required = false)List<MultipartFile> photos
                                                  ){
        return new ResponseEntity<Enfant>(enfantService.creer(collectData, photos), HttpStatus.CREATED);
    }

}
