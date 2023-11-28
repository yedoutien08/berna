// jwt-auth.service.ts (service d'authentification avec le token)
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TokenStorageService } from './token-service';

@Injectable({
  providedIn: 'root'
})
export class JwtAuthService {
  private readonly API_BASE_URL ='http://localhost:8000/utilisateur';

  constructor(private http: HttpClient, private tokenStorageService: TokenStorageService) {}

  login(email: string, password: string): Observable<any> {
    const loginData = { email, password };
    return this.http.post(`${this.API_BASE_URL}/connexion`, loginData);
  }
  logout(): void {
    this.tokenStorageService.removeToken();
  }
  saveTokenToLocalStorage(token: string): void {
    this.tokenStorageService.setToken(token);
  }
}




