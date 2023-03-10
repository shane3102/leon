import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormRandomTimesTableComponent } from './form-random-times-table.component';

describe('FormRandomTimesTableComponent', () => {
  let component: FormRandomTimesTableComponent;
  let fixture: ComponentFixture<FormRandomTimesTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormRandomTimesTableComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormRandomTimesTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
