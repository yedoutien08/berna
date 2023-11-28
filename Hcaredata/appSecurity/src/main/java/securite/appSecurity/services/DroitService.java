package securite.appSecurity.services;

import securite.appSecurity.dto.DroitRequest;
import securite.appSecurity.entities.Droit;

import java.util.List;

public interface DroitService {
    Droit create(DroitRequest request);
    Droit read(String codeDroit);
    Droit update(String codeDroit, DroitRequest request);
    Droit delete(String codeDroit);

    List<Droit> getAllDroit();
}
