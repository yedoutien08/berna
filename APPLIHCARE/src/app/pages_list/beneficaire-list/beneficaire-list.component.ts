import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService } from 'src/app/_services/users.service';
import { Beneficiaire } from 'src/app/models/beneficiare';
import { User } from 'src/app/models/users';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-beneficaire-list',
  templateUrl: './beneficaire-list.component.html',
  styleUrls: ['./beneficaire-list.component.css']
})
export class BeneficaireListComponent implements OnInit {
  beneficiaires: Beneficiaire[] = []
  searchTerm!: Event ;
  page = 1;
  pageSize = 4;
  collectionSize!: number;
  currentRate = 8;
  allBeneficiaire: Beneficiaire[] = [];


  ngOnInit(): void {
    this.getBeneficiaires()
  }

  constructor(private _userService:UserService,private router: Router
    ,private route: ActivatedRoute) {
        //Utils._paginationDatatable()
    }

  getBeneficiaires() {
   // let role = "ADMIN"
    this._userService.getAll().subscribe({
      next: data => {
        this.collectionSize = data.length;
       // this.beneficiaires = data
        this.allBeneficiaire = this.beneficiaires;
      },error: error => {
        console.log(error)
      }
    }); 
  }

  search(event: any): void {
    if(event) {
    this.beneficiaires = this.allBeneficiaire.filter((val) => val.non.toLowerCase().includes(event.target.value));
    this.collectionSize = this.beneficiaires.length;
    }
  }

  ajouter(): void {
    this.router.navigate(['/base/beneficiaire'], { relativeTo: this.route })
  }

  getUser(data: any): void {
    this.router.navigate(['/base/beneficiaire'], { relativeTo: this.route, state: data })
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



