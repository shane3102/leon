import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RandomFormBadUiGoodUxComponent } from './random-form-bad-ui-good-ux.component';

describe('RandomFormBadUiGoodUxComponent', () => {
  let component: RandomFormBadUiGoodUxComponent;
  let fixture: ComponentFixture<RandomFormBadUiGoodUxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RandomFormBadUiGoodUxComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RandomFormBadUiGoodUxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
