package securite.appSecurity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import securite.appSecurity.entities.FamilyData;

import java.util.List;

@Data
@NoArgsConstructor
public class CollectData {
    
    private EnfantRequest enfant;
    private List<FamilyDataRequest> familyDataRequests;
    private LocalisationRequest localisation;
    private MedicalDataRequest medicalData;
    private List<ObservationRequest> observations;
    //private List<PhotoRequest> photos;
    private SchoolDataRequest schoolData;
}
