import { Route } from '@angular/router';
import { StringInterpolationComponent } from '../components/string-interpolation/string-interpolation.component';
import { PropertyBindingComponent } from '../components/property-binding/property-binding.component';
import { EventBindingComponent } from '../components/event-binding/event-binding.component';
import { DataBindingContainerComponent } from '../components/data-binding-container/data-binding-container.component';
import { TwoWayDataBindingComponent } from '../components/two-way-data-binding/two-way-data-binding.component';
import { TodoListComponent } from '../components/todo-list/todo-list.component';


export const AppRoutes: Route[] = [
   {
       path: "string-interpolation",
       component: StringInterpolationComponent
   },
   {
       path: "property-binding",
       component: PropertyBindingComponent
   },
   {
       path: "event-binding",
       component: EventBindingComponent
   },
   {
        path: "two-way-data-binding",
        component: TwoWayDataBindingComponent
   },
   {
       path: "all",
       component: DataBindingContainerComponent
   },
   {
       path: "todo-list",
       component: TodoListComponent
   }
];