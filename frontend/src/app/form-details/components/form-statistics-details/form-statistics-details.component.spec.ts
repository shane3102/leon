import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormStatisticsDetailsComponent } from './form-statistics-details.component';

describe('FormStatisticsDetailsComponent', () => {
  let component: FormStatisticsDetailsComponent;
  let fixture: ComponentFixture<FormStatisticsDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormStatisticsDetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormStatisticsDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
