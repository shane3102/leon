import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormFillSingleComponent } from './form-fill-single.component';

describe('FormFillSingleComponent', () => {
  let component: FormFillSingleComponent;
  let fixture: ComponentFixture<FormFillSingleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormFillSingleComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormFillSingleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
