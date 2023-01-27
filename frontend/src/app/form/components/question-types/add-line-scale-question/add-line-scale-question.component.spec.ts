import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddLineScaleQuestionComponent } from './add-line-scale-question.component';

describe('AddLineScaleQuestionComponent', () => {
  let component: AddLineScaleQuestionComponent;
  let fixture: ComponentFixture<AddLineScaleQuestionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddLineScaleQuestionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddLineScaleQuestionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
