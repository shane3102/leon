import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SingleChoiceGoodUiBadUxComponent } from './single-choice-good-ui-bad-ux.component';

describe('SingleChoiceGoodUiBadUxComponent', () => {
  let component: SingleChoiceGoodUiBadUxComponent;
  let fixture: ComponentFixture<SingleChoiceGoodUiBadUxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SingleChoiceGoodUiBadUxComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SingleChoiceGoodUiBadUxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
