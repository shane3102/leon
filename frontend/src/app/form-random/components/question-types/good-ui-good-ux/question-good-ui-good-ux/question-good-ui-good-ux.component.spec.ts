import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuestionGoodUiGoodUxComponent } from './question-good-ui-good-ux.component';

describe('QuestionGoodUiGoodUxComponent', () => {
  let component: QuestionGoodUiGoodUxComponent;
  let fixture: ComponentFixture<QuestionGoodUiGoodUxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ QuestionGoodUiGoodUxComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(QuestionGoodUiGoodUxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
