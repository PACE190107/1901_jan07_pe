import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class TodoService {

    // We are going to inject HttpClient into our TodoService
    // All we need to do is have a private declaration in our constructor
    constructor(private http: HttpClient) {}

    getAllTodos(): Observable<Todo[]> {
        return this.http.get<Todo[]>("http://localhost:8080/PaceServletExamples/rest/todos");
    }
}

export interface Todo {
    id: number;
    title: string;
    body: string;
}