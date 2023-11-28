package securite.appSecurity.services;

import securite.appSecurity.dto.ProfilRequest;
import securite.appSecurity.entities.Profil;

import java.util.List;

public interface ProfilService {
    Profil create(ProfilRequest request);
    Profil read(String codeProfil);
    Profil update(String codeProfil, ProfilRequest request);
    Profil delete(String codeProfil);
    List<Profil> liste();
}
