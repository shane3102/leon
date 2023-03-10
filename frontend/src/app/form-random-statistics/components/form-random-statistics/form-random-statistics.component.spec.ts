import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormRandomStatisticsComponent } from './form-random-statistics.component';

describe('FormRandomStatisticsComponent', () => {
  let component: FormRandomStatisticsComponent;
  let fixture: ComponentFixture<FormRandomStatisticsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormRandomStatisticsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormRandomStatisticsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
