package securite.appSecurity.services;

import securite.appSecurity.dto.VilleRequest;
import securite.appSecurity.entities.Ville;

public interface VilleService {
    Ville creer(VilleRequest request);
    Ville read(int id);
    Ville update(int id, VilleRequest request);
    Ville supprimer(int id);
}
