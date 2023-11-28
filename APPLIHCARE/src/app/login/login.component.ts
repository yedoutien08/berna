import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../_services/auth.service';
import { MatDialog } from '@angular/material/dialog';
import Swal from 'sweetalert2';
import { first } from 'rxjs';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  form!: FormGroup;
  

  constructor(
      private _formBuilder: FormBuilder,
      private _router: Router,
      private _authService:AuthService,
      private dialog: MatDialog
  ){}

 /* signIn(){
    this._router.navigateByUrl('/base/dashboard');
  }*/

  ngOnInit() {
    this.initForm()
  }

  initForm() {
     this.form = this._formBuilder.group({
          email : ['', [Validators.required, Validators.email]],
          password   : ['', [Validators.required]]
     });
  }

  get email(){
    return this.form.get('email')
  }
  get password(){
    return this.form.get('password')
  }

  signIn() {
    this._authService.signIn(this.form.value).pipe(first()).subscribe({
      next: () => {

        if(localStorage.hasOwnProperty('access_token')) {
          this.initForm()
          Swal.fire('Enrégistrement', 'Opération effectuée avec succes', 'success').then(() => {
            this._router.navigateByUrl('/base/dashboard').then(()=>{
              window.location.reload()
            });
          })

        } else {
          Swal.fire('Echec','Paramètres de connexion incohérents','error')
        }
      },error: error => {
        console.log(error)
        Swal.fire('Echec','Authentification échouée','error')
      }
    })
  }
  
}

