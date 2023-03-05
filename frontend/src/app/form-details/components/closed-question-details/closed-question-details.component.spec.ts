import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClosedQuestionDetailsComponent } from './closed-question-details.component';

describe('ClosedQuestionDetailsComponent', () => {
  let component: ClosedQuestionDetailsComponent;
  let fixture: ComponentFixture<ClosedQuestionDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClosedQuestionDetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ClosedQuestionDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
