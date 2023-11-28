import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ProfilService } from 'src/app/_services/profil.service';
import { UserService } from 'src/app/_services/users.service';
import { Profil } from 'src/app/models/profil';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-profil-list',
  templateUrl: './profil-list.component.html',
  styleUrls: ['./profil-list.component.css']
})
export class ProfilListComponent {
  profils: Profil[] = []
  searchTerm!: Event ;
  page = 1;
  pageSize = 4;
  collectionSize!: number;
  currentRate = 8;
  allProfils: Profil[] = [];


  ngOnInit(): void {
    this.getProfils()
  }

  constructor(private _profilService:ProfilService,private router: Router
    ,private route: ActivatedRoute) {
        //Utils._paginationDatatable()
    }

  getProfils() {
   // let role = "ADMIN"
    this._profilService.getAll().subscribe({
      next: data => {
        this.collectionSize = data.length;
        this.profils = data
        this.allProfils = this.profils;
      },error: error => {
        console.log(error)
      }
    }); 
  }

  search(event: any): void {
    if(event) {
    this.profils = this.allProfils.filter((val) => val.libelleProfil.toLowerCase().includes(event.target.value));
    this.collectionSize = this.profils.length;
    }
  }

  ajouter(): void {
    this.router.navigate(['/base/profil'], { relativeTo: this.route })
  }

  getProfil(data: any): void {
    this.router.navigate(['/base/profil'], { relativeTo: this.route, state: data })
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
    this._profilService.deleteProfil(id).subscribe({
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




