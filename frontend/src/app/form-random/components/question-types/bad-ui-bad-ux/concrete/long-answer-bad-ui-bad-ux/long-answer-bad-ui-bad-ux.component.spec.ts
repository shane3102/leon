import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LongAnswerBadUiBadUxComponent } from './long-answer-bad-ui-bad-ux.component';

describe('LongAnswerBadUiBadUxComponent', () => {
  let component: LongAnswerBadUiBadUxComponent;
  let fixture: ComponentFixture<LongAnswerBadUiBadUxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LongAnswerBadUiBadUxComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LongAnswerBadUiBadUxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
