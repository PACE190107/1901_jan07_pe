import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-event-binding',
  templateUrl: './event-binding.component.html',
  styleUrls: ['./event-binding.component.css']
})
export class EventBindingComponent implements OnInit {

  private counter: number = 0;
  private colors: string[] = ["black", "red", "orange", "yellow", "green", "blue", "purple"];

  constructor() { }

  ngOnInit() {
  }

  get currentColor(): string {
    return this.colors[this.counter];
  }

  cycleColor(): void {
    if (this.counter === 7) {
      this.counter = 0;
    }
    this.counter++;
  }

  clickExample(): void {
    alert("This alert was trigged by invoking a (click) event!");
  }


}
