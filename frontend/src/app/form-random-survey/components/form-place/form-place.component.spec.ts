import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormPlaceComponent } from './form-place.component';

describe('FormPlaceComponent', () => {
  let component: FormPlaceComponent;
  let fixture: ComponentFixture<FormPlaceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormPlaceComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormPlaceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
