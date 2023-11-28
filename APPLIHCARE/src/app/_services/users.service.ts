// user.service.ts (service pour obtenir les informations de l'utilisateur)
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, of } from 'rxjs';
import { User } from '../models/users';
import { apiConfig } from '../config';
import { TokenStorageService } from './token-service';
import { NewPassword } from '../models/newPassword';

@Injectable({
  providedIn: 'root'
})
export class UserService { 

  baseUrl: string = apiConfig.hcareHost + '/utilisateur'
  

  constructor(private http: HttpClient, private tokenStorageService: TokenStorageService) {}


addUser(user: User): Observable<any> {
  return this.http.post<any>(`${this.baseUrl}/${'creer'}`, user)
}

newpassword(newPassword:NewPassword): Observable<any> {
  return this.http.post<any>(`${this.baseUrl}/${'resetPassword'}`,newPassword)
}

forgotpassword(newPassword:NewPassword): Observable<any> {
  return this.http.post<any>(`${this.baseUrl}/${'forgotpassword'}`,newPassword)
}

readUser(codeutilisateur:number): Observable<any> {
  return this.http.get<any>(`${this.baseUrl}/${'read'}/${codeutilisateur}`)
}

updateUSer(user: any): Observable<any> {
  return this.http.put<any>(`${this.baseUrl}/${'update'}/${user.codeUtilisateur}`, user);
}

getAll(): Observable<User[]>{
  return this.http.get<User[]>(`${this.baseUrl}/${'liste'}`);
}

deleteUser(codeutilisateur: number): Observable<any> {
  return this.http.delete(`${this.baseUrl}supprimer'}/${codeutilisateur}`)
}

}











