import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormRandomPlacesBarGraphComponent } from './form-random-places-bar-graph.component';

describe('FormRandomPlacesBarGraphComponent', () => {
  let component: FormRandomPlacesBarGraphComponent;
  let fixture: ComponentFixture<FormRandomPlacesBarGraphComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormRandomPlacesBarGraphComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormRandomPlacesBarGraphComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
