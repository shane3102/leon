import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LineScaleGoodUiBadUxComponent } from './line-scale-good-ui-bad-ux.component';

describe('LineScaleGoodUiBadUxComponent', () => {
  let component: LineScaleGoodUiBadUxComponent;
  let fixture: ComponentFixture<LineScaleGoodUiBadUxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LineScaleGoodUiBadUxComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LineScaleGoodUiBadUxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
