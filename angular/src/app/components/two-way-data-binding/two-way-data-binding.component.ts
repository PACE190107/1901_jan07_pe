import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-two-way-data-binding',
  templateUrl: './two-way-data-binding.component.html',
  styleUrls: ['./two-way-data-binding.component.css']
})
export class TwoWayDataBindingComponent implements OnInit {

  private user: User = {
    username: '',
    password: ''
  }

  constructor() { }

  ngOnInit() {
  }

}



export interface User {
  username: string;
  password: string;
}