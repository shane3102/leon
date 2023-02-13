import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuestionBadUiBadUxComponent } from './question-bad-ui-bad-ux.component';

describe('QuestionBadUiBadUxComponent', () => {
  let component: QuestionBadUiBadUxComponent;
  let fixture: ComponentFixture<QuestionBadUiBadUxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ QuestionBadUiBadUxComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(QuestionBadUiBadUxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
