import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/users';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.scss']
})
export class NavComponent implements OnInit{
  currentUser!: User
  ngOnInit(){
    this.retrieveConnectedUser()
  }

  constructor(private _router: Router){}

  retrieveConnectedUser(){
    let retrievedObject  = localStorage.getItem('currentUser');
      console.log("info user "+ retrievedObject)
      if (retrievedObject !== null && retrievedObject !== undefined) {
        this.currentUser = JSON.parse(retrievedObject); 
      }
  }

  deconnexion(){
    localStorage.clear()
    this._router.navigate([""])
  }
}
