import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { first } from 'rxjs';
import { AuthService } from 'src/app/_services/auth.service';
import { ProfilService } from 'src/app/_services/profil.service';
import { Profil } from 'src/app/models/profil';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.css']
})
export class ProfilComponent {
  form!: FormGroup

  constructor(private _formBuilder: FormBuilder,private _router: Router, private route: ActivatedRoute,
    private _authService: AuthService,
    private _profilService: ProfilService,
    ) { }

    


  ngOnInit() {
    this.initForm()
   // this.form.get('confirmpassword')?.setValue(this.form.value.password)
    this.form.patchValue(history.state)
    if (history.state.codeProfil) {
      this.form.patchValue({
        codeProfil: history.state.codeProfil,
        libelleProfil: history.state.libelleProfil,
      })
    }
  }

  initForm() {
    this.form = this._formBuilder.group({
      codeProfil: [''],
      libelleProfil: ['', [Validators.required]],
    });
  }

  get nom(){
    return this.form.get('libelleProfil')
  }
 
 
  annulerForm() {
    this.initForm()
  }

 
  onNameInput(event: any) {
    const input = event.target;
    const value = input.value;
    const pattern = /^[a-zA-Z]*$/;

    if (!pattern.test(value)) {
      input.value = value.replace(/[^a-zA-Z]/g, ''); // Filtrer les caractères non autorisés
    }

    this.form.controls['libelleProfil'].setValue(input.value);
  }


 
  submitForm() {
    if(this.form.invalid){
      return;
    }
      const resultat: Profil = Object.assign({}, this.form.value)
      if (!this.form.value.codeProfil) {
        this.save(resultat)
      } else {
        this.update(resultat)
      }
    }
     

  save(profil: Profil) {
    this._profilService.addProfil(profil).pipe(first()).subscribe({
  
      next: () => {
        Swal.fire('Enrégistrement', 'Opération effectuée avec succes', 'success').then(() => {
          this._router.navigateByUrl('/base/profil-list');
        })
       
      }, error: error => {
        console.log(error)
        Swal.fire('Enrégistrement echoué', error.error.message, 'error')
      }
    });
  }


  update(profil: Profil) {
    this._profilService.updateProfil(profil).pipe(first()).subscribe({
      next: () => {
        Swal.fire('Mise à jour', 'Opération effectuée avec succes', 'success')
        this._router.navigateByUrl('/base/profil');
      }, error: error => {
        console.log(error)
        Swal.fire('Enrégistrement', error.error.message, 'error')
      }
    });
  }

  

  


}

