import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, of } from 'rxjs';
import { apiConfig } from '../config';
import { TokenStorageService } from './token-service';
import { Profil } from '../models/profil';

@Injectable({
  providedIn: 'root'
})
export class ProfilService { 

  baseUrl: string = apiConfig.hcareHost + '/profil'
  

  constructor(private http: HttpClient, private tokenStorageService: TokenStorageService) {}


addProfil(profil: Profil): Observable<any> {
  return this.http.post<any>(`${this.baseUrl}/${'create'}`, profil)
}
 
readProfil(id:number): Observable<any> {
      return this.http.get<any>(`${this.baseUrl}/${'read'}/${id}`)
    }
    
  updateProfil(profil: Profil): Observable<any> {
      return this.http.put<any>(`${this.baseUrl}/${'update'}/${profil.codeProfil}`, profil);
    }
    
  getAll(): Observable<Profil[]>{
      return this.http.get<Profil[]>(`${this.baseUrl}/${'liste'}`);
    }
    
  deleteProfil(id: number): Observable<any> {
      return this.http.delete(`${this.baseUrl}delete'}/${id}`)
    }
  


}