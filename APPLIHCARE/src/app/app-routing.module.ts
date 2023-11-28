import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BaseComponent } from './base/base.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { LoginComponent } from './login/login.component';
import { AuthGuard } from './guard/auth.guard';
import { BeneficiaireComponent } from './pages_add/beneficiaire/beneficiaire.component';
import { UsersComponent } from './pages_add/users/users.component';
import { UsersListComponent } from './pages_list/users-list/users-list.component';
import { LoNewpasswordComponent } from './lo-newpassword/lo-newpassword.component';
import { BeneficaireListComponent } from './pages_list/beneficaire-list/beneficaire-list.component';
import { DroitListComponent } from './pages_list/droit-list/droit-list.component';
import { ProfilListComponent } from './pages_list/profil-list/profil-list.component';
import { DroitComponent } from './pages_add/droit/droit.component';
import { ProfilComponent } from './pages_add/profil/profil.component';

const routes: Routes = [

 { path: '', component : LoginComponent },
 { path: 'lo-newpassword', component: LoNewpasswordComponent},
 { path: 'base', component: BaseComponent , 
 canActivate:[AuthGuard],
 canActivateChild: [AuthGuard],children : [
  {path: 'dashboard', component: DashboardComponent}   ,
  {path: 'users', component: UsersComponent},
  {path: 'beneficiaire', component: BeneficiaireComponent},
  {path: 'droit', component: DroitComponent},
  {path: 'profil', component: ProfilComponent},
  {path: 'beneficaire-list', component: BeneficaireListComponent},
  {path: 'droit-list', component: DroitListComponent},
  {path: 'profil-list', component: ProfilListComponent},
  {path: 'users-list', component: UsersListComponent}

]
}
];

  

  

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
