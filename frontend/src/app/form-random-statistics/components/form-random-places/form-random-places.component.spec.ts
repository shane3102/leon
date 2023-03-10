import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormRandomPlacesComponent } from './form-random-places.component';

describe('FormRandomPlacesComponent', () => {
  let component: FormRandomPlacesComponent;
  let fixture: ComponentFixture<FormRandomPlacesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormRandomPlacesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormRandomPlacesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
