import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LongAnswerGoodUiBadUxComponent } from './long-answer-good-ui-bad-ux.component';

describe('LongAnswerGoodUiBadUxComponent', () => {
  let component: LongAnswerGoodUiBadUxComponent;
  let fixture: ComponentFixture<LongAnswerGoodUiBadUxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LongAnswerGoodUiBadUxComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LongAnswerGoodUiBadUxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
