import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DataBindingContainerComponent } from './data-binding-container.component';

describe('DataBindingContainerComponent', () => {
  let component: DataBindingContainerComponent;
  let fixture: ComponentFixture<DataBindingContainerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DataBindingContainerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DataBindingContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
