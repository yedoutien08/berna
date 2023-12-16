package securite.appSecurity.services;

import org.springframework.web.multipart.MultipartFile;
import securite.appSecurity.dto.CollectData;
import securite.appSecurity.entities.Enfant;

import java.util.List;

public interface EnfantService {
    Enfant creer(CollectData request, List<MultipartFile> photos);
    Enfant modifier(int id,CollectData request);
    Enfant lire(int id);
    Enfant supprimer(int id);
    List<Enfant> liste();


}
