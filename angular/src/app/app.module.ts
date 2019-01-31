import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { DataBindingContainerComponent } from './components/data-binding-container/data-binding-container.component';
import { StringInterpolationComponent } from './components/string-interpolation/string-interpolation.component';
import { EventBindingComponent } from './components/event-binding/event-binding.component';
import { PropertyBindingComponent } from './components/property-binding/property-binding.component';
import { TwoWayDataBindingComponent } from './components/two-way-data-binding/two-way-data-binding.component';

@NgModule({
  declarations: [
    AppComponent,
    DataBindingContainerComponent,
    StringInterpolationComponent,
    EventBindingComponent,
    PropertyBindingComponent,
    TwoWayDataBindingComponent
  ],
  imports: [
    BrowserModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
