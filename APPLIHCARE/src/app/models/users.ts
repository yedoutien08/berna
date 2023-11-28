import { Droit } from "./droit"
import { Profil } from "./profil"

export interface User {
    codeUtilisateur: number
    nom: string
    prenom: string
    email: string
    sexe: string
    statut: number
    password: string
    profil: Profil
    droit: Droit[]


  }