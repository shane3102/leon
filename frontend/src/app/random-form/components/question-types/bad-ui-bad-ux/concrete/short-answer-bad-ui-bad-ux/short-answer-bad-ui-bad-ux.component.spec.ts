import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShortAnswerBadUiBadUxComponent } from './short-answer-bad-ui-bad-ux.component';

describe('ShortAnswerBadUiBadUxComponent', () => {
  let component: ShortAnswerBadUiBadUxComponent;
  let fixture: ComponentFixture<ShortAnswerBadUiBadUxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShortAnswerBadUiBadUxComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShortAnswerBadUiBadUxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
