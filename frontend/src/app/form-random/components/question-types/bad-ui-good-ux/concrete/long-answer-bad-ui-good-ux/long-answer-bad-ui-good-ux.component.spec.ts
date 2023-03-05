import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LongAnswerBadUiGoodUxComponent } from './long-answer-bad-ui-good-ux.component';

describe('LongAnswerBadUiGoodUxComponent', () => {
  let component: LongAnswerBadUiGoodUxComponent;
  let fixture: ComponentFixture<LongAnswerBadUiGoodUxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LongAnswerBadUiGoodUxComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LongAnswerBadUiGoodUxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
