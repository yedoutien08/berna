import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, of } from 'rxjs';
import { apiConfig } from '../config';
import { TokenStorageService } from './token-service';
import { Ville } from '../models/ville';

@Injectable({
  providedIn: 'root'
})
export class VilleService { 

  baseUrl: string = apiConfig.hcareHost + '/droit'
  

  constructor(private http: HttpClient, private tokenStorageService: TokenStorageService) {}


addVille(ville: Ville): Observable<any> {
  return this.http.post<any>(`${this.baseUrl}/${'create'}`, ville)
}

readVille(id:number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/${'read'}/${id}`)
  }
  
updateVille(ville: Ville): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/${'update'}/${ville.id}`, ville);
  }
  
getAll(): Observable<any>{
    return this.http.get<any>(`${this.baseUrl}/${'all'}`);
  }

villeByPays(idPays:number): Observable<any>{
    return this.http.get<any>(`${this.baseUrl}/${'allByPays'}/${idPays}`);
  }
  
deleteVille(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}delete'}/${id}`)
  }


}