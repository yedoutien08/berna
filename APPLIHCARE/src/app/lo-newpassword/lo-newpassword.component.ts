import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { first } from 'rxjs';
import Swal from 'sweetalert2';
import { AuthService } from '../_services/auth.service';
import { UserService } from '../_services/users.service';
import { NewPassword } from '../models/newPassword';

@Component({
  selector: 'app-lo-newpassword',
  templateUrl: './lo-newpassword.component.html',
  styleUrls: ['./lo-newpassword.component.css']
})
export class LoNewpasswordComponent {
  form!: FormGroup;
  

  constructor(
      private _formBuilder: FormBuilder,
      private _router: Router,
      private _userService:UserService,
      private dialog: MatDialog
  ){}

  ngOnInit() {
    this.initForm();
  
   /* this.form.patchValue(history.state);
  
    if (history.state.email) {
      this.form.patchValue({
        email: history.state.email,
        password: history.state.password,
        oldPassword: history.state.oldPassword,
        newPassword: history.state.newPassword,
      });
    }*/
  }
  

  initForm() {
     this.form = this._formBuilder.group({
          email : ['', [Validators.required]],
          password   : ['', Validators.required],
          oldPassword:['', Validators.required],
          newPassword:['', Validators.required]
     });
  }
 
 

  newPasswords() {
   // console.log(this.form.value)
    const requestData: NewPassword = Object.assign({}, this.form.value)
    console.log("form data "+ JSON.stringify(requestData))
    this._userService.newpassword(requestData).pipe(first()).subscribe({
      next: () => {
         Swal.fire({
          title : "Réussite",
          text: "Mot de passe changé avec succès",
          confirmButtonText:"Ok",
          showConfirmButton :true 
         }).then((result)=>{
          if(result.isConfirmed){
            this._router.navigate([""])
          }
         })
      },error: error => {
        console.log(error)
        Swal.fire('Echec',error,'error')
      }
    })
  }


}