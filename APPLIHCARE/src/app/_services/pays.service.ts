import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, of } from 'rxjs';
import { apiConfig } from '../config';
import { TokenStorageService } from './token-service';
import { Pays } from '../models/pays';

@Injectable({
  providedIn: 'root'
})
export class PaysService { 

  baseUrl: string = apiConfig.hcareHost + '/pays'
  

  constructor(private http: HttpClient, private tokenStorageService: TokenStorageService) {}


addPays(pays: Pays): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/${'create'}`, pays)
}
   
readPays(id:number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/${'read'}/${id}`)
}
      
updatePays(pays: Pays): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/${'update'}/${pays.id}`, pays);
}
      
getAll(): Observable<any>{
    return this.http.get<any>(`${this.baseUrl}/${'liste'}`);
}
      
deletePays(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}delete'}/${id}`)
}


}