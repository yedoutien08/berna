import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, of } from 'rxjs';
import { apiConfig } from '../config';
import { TokenStorageService } from './token-service';
import { Droit } from '../models/droit';

@Injectable({
  providedIn: 'root'
})
export class DroitService { 

  baseUrl: string = apiConfig.hcareHost + '/droit'
  

  constructor(private http: HttpClient, private tokenStorageService: TokenStorageService) {}

addDroit(droit: Droit): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/${'create'}`, droit)
}
   
readDroit(id:number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/${'read'}/${id}`)
}
      
updateDroit(droit: Droit): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/${'update'}/${droit.codeDroit}`, droit);
}
      
getAll(): Observable<Droit[]>{
    return this.http.get<Droit[]>(`${this.baseUrl}/${'liste'}`);
}
      
deleteDroit(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}delete'}/${id}`)
}


}


