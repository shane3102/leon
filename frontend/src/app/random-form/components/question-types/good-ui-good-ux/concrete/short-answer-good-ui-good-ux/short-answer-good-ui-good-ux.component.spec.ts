import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShortAnswerGoodUiGoodUxComponent } from './short-answer-good-ui-good-ux.component';

describe('ShortAnswerGoodUiGoodUxComponent', () => {
  let component: ShortAnswerGoodUiGoodUxComponent;
  let fixture: ComponentFixture<ShortAnswerGoodUiGoodUxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShortAnswerGoodUiGoodUxComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShortAnswerGoodUiGoodUxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
