import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LongAnswerGoodUiGoodUxComponent } from './long-answer-good-ui-good-ux.component';

describe('LongAnswerGoodUiGoodUxComponent', () => {
  let component: LongAnswerGoodUiGoodUxComponent;
  let fixture: ComponentFixture<LongAnswerGoodUiGoodUxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LongAnswerGoodUiGoodUxComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LongAnswerGoodUiGoodUxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
