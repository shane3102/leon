import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MultipleChoiceGoodUiGoodUxComponent } from './multiple-choice-good-ui-good-ux.component';

describe('MultipleChoiceGoodUiGoodUxComponent', () => {
  let component: MultipleChoiceGoodUiGoodUxComponent;
  let fixture: ComponentFixture<MultipleChoiceGoodUiGoodUxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MultipleChoiceGoodUiGoodUxComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MultipleChoiceGoodUiGoodUxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
