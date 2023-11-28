import { Injectable } from '@angular/core'
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot, CanActivateChild, UrlTree } from '@angular/router'


@Injectable()
export class AuthGuard implements CanActivate, CanActivateChild {
  constructor(private router : Router) {}

  canActivateChild(childRoute: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    let url: string = state.url;  
     return this.verifyLogin(url);
  }


  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean { 
     let url: string = state.url;  
     return this.verifyLogin(url);
  }


  verifyLogin(url:string) : boolean {
    if(!this.isLoggedIn()){
        this.router.navigate(['/']);
        return false;
    }
    else if(this.isLoggedIn()) {
        return true;
    } else {
        return false;
    } 
}
        
  
    
  public isLoggedIn(): boolean{
        let status = false;
        if( localStorage.getItem('isLoggedIn') == "true"){
          status = true;
        }
        else{
          status = false;
        }
        return status;
  }

  
}
