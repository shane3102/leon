import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddLongAnswerQuestionComponent } from './add-long-answer-question.component';

describe('AddLongAnswerQuestionComponent', () => {
  let component: AddLongAnswerQuestionComponent;
  let fixture: ComponentFixture<AddLongAnswerQuestionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddLongAnswerQuestionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddLongAnswerQuestionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
