import { Enfant } from "./enfant";
import { Famille } from "./familial";
import { Localisation } from "./localisation";
import { Medicales } from "./medicales";
import { Observation } from "./observation";
import { Photos } from "./photo";
import { SchoolData } from "./schoolData";

export interface Beneficiaire{
    non:String,
     EnfantRequest:Enfant[],
     familyDataRequest:Famille[],
     LocalisationRequest: Localisation[],
     MedicalDataRequest :Medicales[],
     ObservationRequest: Observation[],
     PhotoRequest: Photos[],
     SchoolDataRequest: SchoolData[]
}