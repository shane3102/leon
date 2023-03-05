import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShortAnswerBadUiGoodUxComponent } from './short-answer-bad-ui-good-ux.component';

describe('ShortAnswerBadUiGoodUxComponent', () => {
  let component: ShortAnswerBadUiGoodUxComponent;
  let fixture: ComponentFixture<ShortAnswerBadUiGoodUxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShortAnswerBadUiGoodUxComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShortAnswerBadUiGoodUxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
