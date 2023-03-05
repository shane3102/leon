import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddSingleChoiceQuestionComponent } from './add-single-choice-question.component';

describe('AddSingleChoiceQuestionComponent', () => {
  let component: AddSingleChoiceQuestionComponent;
  let fixture: ComponentFixture<AddSingleChoiceQuestionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddSingleChoiceQuestionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddSingleChoiceQuestionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
