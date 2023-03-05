import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShortAnswerGoodUiBadUxComponent } from './short-answer-good-ui-bad-ux.component';

describe('ShortAnswerGoodUiBadUxComponent', () => {
  let component: ShortAnswerGoodUiBadUxComponent;
  let fixture: ComponentFixture<ShortAnswerGoodUiBadUxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShortAnswerGoodUiBadUxComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShortAnswerGoodUiBadUxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
