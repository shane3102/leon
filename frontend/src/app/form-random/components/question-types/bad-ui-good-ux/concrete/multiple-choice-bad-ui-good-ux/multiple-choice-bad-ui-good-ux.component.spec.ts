import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MultipleChoiceBadUiGoodUxComponent } from './multiple-choice-bad-ui-good-ux.component';

describe('MultipleChoiceBadUiGoodUxComponent', () => {
  let component: MultipleChoiceBadUiGoodUxComponent;
  let fixture: ComponentFixture<MultipleChoiceBadUiGoodUxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MultipleChoiceBadUiGoodUxComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MultipleChoiceBadUiGoodUxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
