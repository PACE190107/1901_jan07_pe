import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { DataBindingContainerComponent } from './components/data-binding-container/data-binding-container.component';
import { StringInterpolationComponent } from './components/string-interpolation/string-interpolation.component';
import { EventBindingComponent } from './components/event-binding/event-binding.component';
import { PropertyBindingComponent } from './components/property-binding/property-binding.component';
import { TwoWayDataBindingComponent } from './components/two-way-data-binding/two-way-data-binding.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { AppRoutes } from './shared/app.routes';
import { TodoListComponent } from './components/todo-list/todo-list.component';
import { TodoService } from './services/todo-service';

@NgModule({
  declarations: [
    AppComponent,
    DataBindingContainerComponent,
    StringInterpolationComponent,
    EventBindingComponent,
    PropertyBindingComponent,
    TwoWayDataBindingComponent,
    NavbarComponent,
    TodoListComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule.forRoot(AppRoutes),
    HttpClientModule
  ],
  providers: [TodoService],
  bootstrap: [AppComponent]
})
export class AppModule { }
