import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LineScaleBadUiGoodUxComponent } from './line-scale-bad-ui-good-ux.component';

describe('LineScaleBadUiGoodUxComponent', () => {
  let component: LineScaleBadUiGoodUxComponent;
  let fixture: ComponentFixture<LineScaleBadUiGoodUxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LineScaleBadUiGoodUxComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LineScaleBadUiGoodUxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
