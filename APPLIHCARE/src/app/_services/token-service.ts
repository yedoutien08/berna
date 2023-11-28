
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {
  private readonly TOKEN_KEY = 'accessToken';

  constructor() { }

  // Enregistrer le token dans le localStorage
  setToken(token: string): void {
    localStorage.setItem(this.TOKEN_KEY, token);
  }

  // Obtenir le token à partir du localStorage
  getToken(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }

  // Supprimer le token du localStorage
  removeToken(): void {
    localStorage.removeItem(this.TOKEN_KEY);
  }
}
