import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormRandomTimesComponent } from './form-random-times.component';

describe('FormRandomTimesComponent', () => {
  let component: FormRandomTimesComponent;
  let fixture: ComponentFixture<FormRandomTimesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormRandomTimesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormRandomTimesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
