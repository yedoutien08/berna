package securite.appSecurity.services;

import securite.appSecurity.dto.CollectData;
import securite.appSecurity.entities.Enfant;

import java.util.List;

public interface EnfantService {
    Enfant creer(CollectData request);
    Enfant modifier(int id,CollectData request);
    Enfant lire(int id);
    Enfant supprimer(int id);
    List<Enfant> liste();


}
