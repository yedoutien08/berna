import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, first, of, switchMap } from 'rxjs';
import { apiConfig } from '../config';



const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})

export class AuthService {
  isLoggedIn$  = new BehaviorSubject(false)
  isLoggedIn: any;
  router: any;
  userData: any;

  logout() {
    // clear any current data
    this.clearData();
    location.reload()
    // tell the rest of the application about the logout
    //this.isLoggedIn.next(false);
    this.router.navigate(['/']);
    
    //return true;
  }

  clearData() {
    this.userData = null
    localStorage.clear();
  }


  constructor(private http:HttpClient) { }

  //baseUrl: string = apiConfig.lavageHost + '/auth/authenticate'
  baseUrl: string = apiConfig.hcareHost + '/utilisateur'

   signIn(credentials: { email: string; password: string }): Observable<any> {

        return this.http.post(`${this.baseUrl}/connexion`, credentials,httpOptions).pipe(
            switchMap((response: any) => {
                // Store the user on the user service
                console.log("response "+ response)
                if(response != null && response != undefined) {
                  localStorage.setItem('isLoggedIn', "true");
                  localStorage.setItem('access_token', JSON.stringify(response.token));
                  //this.isLoggedIn.next(true);
                  if (localStorage.hasOwnProperty('access_token')) {
                    this.http.get(`${this.baseUrl}/profile`).pipe(first()).subscribe({
                      next: (data) => {
                        console.log("utilisateur")
                        console.log(data)
                        localStorage.setItem('currentUser', JSON.stringify(data));
                      }
                    });
                  }
                }
                // Return a new observable with the response
                return of(response);
            })

            
        );
        


    }

  
}

