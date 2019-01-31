import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-string-interpolation',
  templateUrl: './string-interpolation.component.html',
  styleUrls: ['./string-interpolation.component.css']
})
export class StringInterpolationComponent implements OnInit {

  readonly ourCompany: string = "Tesla";
  readonly timestampe: Date = new Date();

  constructor() { }

  ngOnInit() {
  }

}
