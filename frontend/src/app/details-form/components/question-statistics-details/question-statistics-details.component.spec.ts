import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuestionStatisticsDetailsComponent } from './question-statistics-details.component';

describe('QuestionStatisticsDetailsComponent', () => {
  let component: QuestionStatisticsDetailsComponent;
  let fixture: ComponentFixture<QuestionStatisticsDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ QuestionStatisticsDetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(QuestionStatisticsDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
