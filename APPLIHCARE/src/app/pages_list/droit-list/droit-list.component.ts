import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { DroitService } from 'src/app/_services/droit.service';
import { Droit } from 'src/app/models/droit';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-droit-list',
  templateUrl: './droit-list.component.html',
  styleUrls: ['./droit-list.component.css']
})
export class DroitListComponent {
  droits: Droit[] = []
  searchTerm!: Event ;
  page = 1;
  pageSize = 4;
  collectionSize!: number;
  currentRate = 8;
  allDroits: Droit[] = [];


  ngOnInit(): void {
    this.getDroits()
  }

  constructor(private _droitService:DroitService,private router: Router
    ,private route: ActivatedRoute) {
        //Utils._paginationDatatable()
    }

  getDroits() {
    //let role = "ADMIN"
    this._droitService.getAll().subscribe({
      next: data => {
        this.collectionSize = data.length;
        this.droits = data
        this.allDroits = this.droits;
      },error: error => {
        console.log(error)
      }
    }); 
  }

  search(event: any): void {
    if(event) {
    this.droits = this.allDroits.filter((val) => val.libelleDroit.toLowerCase().includes(event.target.value));
    this.collectionSize = this.droits.length;
    }
  }

  ajouter(): void {
    this.router.navigate(['/base/droit'], { relativeTo: this.route })
  }

  getDroit(data: any): void {
    this.router.navigate(['/base/droit'], { relativeTo: this.route, state: data })
  }

 

  confirmBox(user:any){
    Swal.fire({
      title: 'Etes-vous sûre de vouloir supprimer?',
      text: 'Suppression droit',
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
    this._droitService.deleteDroit(id).subscribe({
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