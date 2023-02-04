import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LineScaleBadUiBadUxComponent } from './line-scale-bad-ui-bad-ux.component';

describe('LineScaleBadUiBadUxComponent', () => {
  let component: LineScaleBadUiBadUxComponent;
  let fixture: ComponentFixture<LineScaleBadUiBadUxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LineScaleBadUiBadUxComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LineScaleBadUiBadUxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
