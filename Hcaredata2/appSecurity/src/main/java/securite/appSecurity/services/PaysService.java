package securite.appSecurity.services;

import securite.appSecurity.dto.PaysRequest;
import securite.appSecurity.dto.VilleRequest;
import securite.appSecurity.entities.Pays;
import securite.appSecurity.entities.Ville;

import java.util.List;

public interface PaysService {
    Pays creer(PaysRequest request);
    Pays read(int id);
    Pays update(int id, PaysRequest request);
    Pays supprimer(int id);
    List<Pays> getAll();
}
