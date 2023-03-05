import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SingleChoiceBadUiGoodUxComponent } from './single-choice-bad-ui-good-ux.component';

describe('SingleChoiceBadUiGoodUxComponent', () => {
  let component: SingleChoiceBadUiGoodUxComponent;
  let fixture: ComponentFixture<SingleChoiceBadUiGoodUxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SingleChoiceBadUiGoodUxComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SingleChoiceBadUiGoodUxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
