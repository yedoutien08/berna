import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/users';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {
  currentUser !:User;

  ngOnInit(){
    this.retrieveConnectedUser()
  }

  constructor(){}

  retrieveConnectedUser(){
    let retrievedObject = localStorage.getItem('currentUser');
      if (retrievedObject !== null && retrievedObject !== undefined) {
        this.currentUser = JSON.parse(retrievedObject); 
      }
  }
}
