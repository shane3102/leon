import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RandomFormGoodUiGoodUxComponent } from './random-form-good-ui-good-ux.component';

describe('RandomFormGoodUiGoodUxComponent', () => {
  let component: RandomFormGoodUiGoodUxComponent;
  let fixture: ComponentFixture<RandomFormGoodUiGoodUxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RandomFormGoodUiGoodUxComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RandomFormGoodUiGoodUxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
