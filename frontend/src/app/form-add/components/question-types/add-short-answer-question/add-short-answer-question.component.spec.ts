import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddShortAnswerQuestionComponent } from './add-short-answer-question.component';

describe('AddShortAnswerQuestionComponent', () => {
  let component: AddShortAnswerQuestionComponent;
  let fixture: ComponentFixture<AddShortAnswerQuestionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddShortAnswerQuestionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddShortAnswerQuestionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
