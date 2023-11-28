import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { first } from 'rxjs';
import { UserService } from 'src/app/_services/users.service';
import { User } from 'src/app/models/users';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.css']
})
export class UsersListComponent implements OnInit{
  users: User[] = []
  searchTerm!: Event ;
  page = 1;
  pageSize = 4;
  collectionSize!: number;
  currentRate = 8;
  allUsers: User[] = [];


  ngOnInit(): void {
    this.getUsers()
  }

  constructor(private _userService:UserService,private router: Router
    ,private route: ActivatedRoute) {
        //Utils._paginationDatatable()
    }

  getUsers() {
   // let role = "ADMIN"
    this._userService.getAll().pipe(first()).subscribe({
      next: data => {
        this.collectionSize = data.length;
        this.users = data
        this.allUsers = this.users;
        console.log('allUsers'+this.allUsers)
       // this.allUsers.forEach(us=>{
        //  console.log("utilisateur profil "+us.profil.libelle)
        //}
        //)
      },error: error => {
        console.log(error)
      }
    }); 
  }

  search(event: any): void {
    if(event) {
    this.users = this.allUsers.filter((val) => val.nom.toLowerCase().includes(event.target.value));
    this.collectionSize = this.users.length;
    }
  }

  ajouter(): void {
    this.router.navigate(['/base/users'], { relativeTo: this.route })
  }

  getUser(data: any): void {
    this.router.navigate(['/base/users'], { relativeTo: this.route, state: data })
  }

 

  confirmBox(user:any){
    Swal.fire({
      title: 'Etes-vous sûre de vouloir supprimer?',
      text: 'Suppression utilisateur!',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Oui',
      cancelButtonText: 'Non'
    }).then((result:any) => {
      if (result.value) {
        this.delete(user.id)
      } 
      
      else if (result.dismiss === Swal.DismissReason.cancel) {
        Swal.fire(
          'Cancelled',
          'Your imaginary file is safe :)',
          'error'
        )
      }
      
    })
  }

  delete(id:number) {
    this._userService.deleteUser(id).subscribe({
      next: () => {
        this.ngOnInit()
        Swal.fire(
          'Suppression!',
          'Opération effectuée avec succès.',
          'success'
        )
      },error: error => {
        console.log(error)
        Swal.fire(
          'Suppression!',
          'Echec opération.',
          'error'
        )
      }
    }
    )
  }

}



