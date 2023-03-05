import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuestionGoodUiBadUxComponent } from './question-good-ui-bad-ux.component';

describe('QuestionGoodUiBadUxComponent', () => {
  let component: QuestionGoodUiBadUxComponent;
  let fixture: ComponentFixture<QuestionGoodUiBadUxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ QuestionGoodUiBadUxComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(QuestionGoodUiBadUxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
