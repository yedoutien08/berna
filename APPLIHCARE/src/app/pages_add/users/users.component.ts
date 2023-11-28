import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { first } from 'rxjs';
import { AuthService } from 'src/app/_services/auth.service';
import { ProfilService } from 'src/app/_services/profil.service';
import { UserService } from 'src/app/_services/users.service';
import { Profil } from 'src/app/models/profil';
import { User } from 'src/app/models/users';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent {
  form!: FormGroup
  listProfils : Profil[] = []

  constructor(private _formBuilder: FormBuilder,private _router: Router, private route: ActivatedRoute,
    private _authService: AuthService,
    private _userService: UserService,
    private _profilService : ProfilService
    ) { }

    


  ngOnInit() {
    this.initForm()
    this.getProfils()
   // this.form.get('confirmpassword')?.setValue(this.form.value.password)
    this.form.patchValue(history.state)
    if (history.state.id) {
      this.form.patchValue({
        id: history.state.id,
        nom: history.state.nom,
        prenom: history.state.prenom,
        password: history.state.password,
        email: history.state.email,
        sexe: history.state.sexe,
        profil: history.state.profil,
      })
    }
  }

  initForm() {
    this.form = this._formBuilder.group({
      id: [''],
      nom: ['', [Validators.required]],
      prenom: ['', [Validators.required]],
      password: ['', Validators.required],
      email: ['', Validators.required],
      sexe: ['', [Validators.required]],
      profil: ['', [Validators.required]]
    });
  }

  get nom(){
    return this.form.get('nom')
  }
  get prenom(){
    return this.form.get('prenom')
  }
 
  get password(){
    return this.form.get('password')
  }
  get email(){
    return this.form.get('email')
  }
  get sexe(){
    return this.form.get('sexe')
  }
 
  get profil(){
    return this.form.get('profil')
  }
  annulerForm() {
    this.initForm()
  }

  getProfils(){
    this._profilService.getAll().pipe(first()).subscribe({
      next: (data) =>{
          this.listProfils = data
          console.log("les profils "+data)
      },
      error: (error)=>{
        console.log(error)
      }
    })
  }



  onNameInput(event: any) {
    const input = event.target;
    const value = input.value;
    const pattern = /^[a-zA-Z]*$/;

    if (!pattern.test(value)) {
      input.value = value.replace(/[^a-zA-Z]/g, ''); // Filtrer les caractères non autorisés
    }

    this.form.controls['nom'].setValue(input.value);
  }

  onNameInput1(event: any) {
    const input = event.target;
    const value = input.value;
    const pattern = /^[0-9]*$/;

    if (!pattern.test(value)) {
      input.value = value.replace(/[^0-9]/g, ''); // Filtrer les caractères non autorisés
    }

    this.form.controls['phonenumber'].setValue(input.value);
  }


  onNameInput2(event: any) {
    const input = event.target;
    const value = input.value;
    const pattern = /^[a-zA-Z]*$/;

    if (!pattern.test(value)) {
      input.value = value.replace(/[^a-zA-Z]/g, ''); // Filtrer les caractères non autorisés
    }

    this.form.controls['prenom'].setValue(input.value);
  }

 
  submitForm() {
    if(this.form.invalid){
      return;
    }
      const resultat: User = Object.assign({}, this.form.value)
      if (!this.form.value.id) {
        this.save(resultat)
      } else {
        this.update(resultat)
      }
    }
     

  save(user: User) {
    this._userService.addUser(user).pipe(first()).subscribe({
  
      next: () => {
        Swal.fire('Enrégistrement', 'Opération effectuée avec succes', 'success').then(() => {
          this._router.navigateByUrl('/base/users-list');
        })
       
      }, error: error => {
        console.log(error)
        Swal.fire('Enrégistrement echoué', error.error.message, 'error')
      }
    });
  }


  update(user: User) {
    this._userService.updateUSer(user).pipe(first()).subscribe({
      next: () => {
        Swal.fire('Mise à jour', 'Opération effectuée avec succes', 'success')
        this._router.navigateByUrl('/base/users');
      }, error: error => {
        console.log(error)
        Swal.fire('Enrégistrement', error.error.message, 'error')
      }
    });
  }

  listeDroits(): void {
    this._router.navigate(['/base/droit-list'], { relativeTo: this.route })
  }

  


}

