import { Component, OnInit } from '@angular/core';
import { TodoService, Todo } from 'src/app/services/todo-service';

@Component({
  selector: 'app-todo-list',
  templateUrl: './todo-list.component.html',
  styleUrls: ['./todo-list.component.css']
})
export class TodoListComponent implements OnInit {

  private todos: Todo[];
  constructor(private todoService: TodoService) { 
    console.log("Constructor")
  }

  ngOnInit() {
    console.log("ngOnInit");
    this.todoService.getAllTodos().subscribe(
      // Two Functions
      // First one is for a successful response
      data => this.todos = data,

      // Second function you provide is for a failed response
      err => console.log(`Error: ${err}`)
    )
  }

}
