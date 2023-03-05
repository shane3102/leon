import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SingleChoiceGoodUiGoodUxComponent } from './single-choice-good-ui-good-ux.component';

describe('SingleChoiceGoodUiGoodUxComponent', () => {
  let component: SingleChoiceGoodUiGoodUxComponent;
  let fixture: ComponentFixture<SingleChoiceGoodUiGoodUxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SingleChoiceGoodUiGoodUxComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SingleChoiceGoodUiGoodUxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
