import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuestionBadUiGoodUxComponent } from './question-bad-ui-good-ux.component';

describe('QuestionBadUiGoodUxComponent', () => {
  let component: QuestionBadUiGoodUxComponent;
  let fixture: ComponentFixture<QuestionBadUiGoodUxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ QuestionBadUiGoodUxComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(QuestionBadUiGoodUxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
