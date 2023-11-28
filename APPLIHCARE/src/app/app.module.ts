import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { AuthGuard } from './guard/auth.guard';
//import { HttpConfigInterceptor } from './interceptor/httpconfig.interceptor';
import { MatDialogModule } from '@angular/material/dialog';
import { CommonModule, DatePipe } from '@angular/common';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatButtonModule } from '@angular/material/button'
import { MatIconModule } from '@angular/material/icon'
import { HighchartsChartModule } from 'highcharts-angular';
import { MatAutocompleteModule } from '@angular/material/autocomplete'
import { ChartModule } from 'angular-highcharts';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BaseComponent } from './base/base.component';
import { NavComponent } from './layout/nav/nav.component';
import { SidebarComponent } from './layout/sidebar/sidebar.component';
import { FooterComponent } from './layout/footer/footer.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { LoginComponent } from './login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { UsersComponent } from './pages_add/users/users.component';
import { BeneficiaireComponent } from './pages_add/beneficiaire/beneficiaire.component';
import { UsersListComponent } from './pages_list/users-list/users-list.component';
import { LoNewpasswordComponent } from './lo-newpassword/lo-newpassword.component';
import { BeneficaireListComponent } from './pages_list/beneficaire-list/beneficaire-list.component';
import { DroitListComponent } from './pages_list/droit-list/droit-list.component';
import { ProfilListComponent } from './pages_list/profil-list/profil-list.component';
import { DroitComponent } from './pages_add/droit/droit.component';

@NgModule({
  declarations: [
    AppComponent,
    BaseComponent,
    NavComponent,
    SidebarComponent,
    FooterComponent,
    DashboardComponent,
    LoginComponent,
    UsersComponent,
    BeneficiaireComponent,
    UsersListComponent,
    LoNewpasswordComponent,
    BeneficaireListComponent,
    DroitListComponent,
    ProfilListComponent,
    DroitComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule,
    MatDialogModule,
    CommonModule,
    MatButtonModule,
    MatIconModule,
    FlexLayoutModule,
    HighchartsChartModule,
    MatAutocompleteModule,
    ChartModule,
    RouterModule,
  ],
  providers: [ AuthGuard,
    DatePipe,
   // { provide: HTTP_INTERCEPTORS, useClass: HttpConfigInterceptor, multi: true }
  ],
    
  bootstrap: [AppComponent]
})
export class AppModule { }
