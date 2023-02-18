import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MultipleChoiceGoodUiBadUxComponent } from './multiple-choice-good-ui-bad-ux.component';

describe('MultipleChoiceGoodUiBadUxComponent', () => {
  let component: MultipleChoiceGoodUiBadUxComponent;
  let fixture: ComponentFixture<MultipleChoiceGoodUiBadUxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MultipleChoiceGoodUiBadUxComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MultipleChoiceGoodUiBadUxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
