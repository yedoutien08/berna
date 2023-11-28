import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, of } from 'rxjs';
import { apiConfig } from '../config';
import { TokenStorageService } from './token-service';
import { Beneficiaire } from '../models/beneficiare';

@Injectable({
  providedIn: 'root'
})
export class BeneficiaireService { 

  baseUrl: string = apiConfig.hcareHost + '/collectData'
  

  constructor(private http: HttpClient, private tokenStorageService: TokenStorageService) {}


addBeneficiaire(beneficiaire: Beneficiaire): Observable<any> {
  return this.http.post<any>(`${this.baseUrl}/${'create'}`, beneficiaire)
}


}











