import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LineScaleGoodUiGoodUxComponent } from './line-scale-good-ui-good-ux.component';

describe('LineScaleGoodUiGoodUxComponent', () => {
  let component: LineScaleGoodUiGoodUxComponent;
  let fixture: ComponentFixture<LineScaleGoodUiGoodUxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LineScaleGoodUiGoodUxComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LineScaleGoodUiGoodUxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
