import { environment } from "./environnement/environnement.component"

export const baseApiConfig = {
    host: environment.serverUrl,
}

export const apiConfig = {
  hcareHost: baseApiConfig.host,


}





