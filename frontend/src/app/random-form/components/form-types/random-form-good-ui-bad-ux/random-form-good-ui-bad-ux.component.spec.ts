import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RandomFormGoodUiBadUxComponent } from './random-form-good-ui-bad-ux.component';

describe('RandomFormGoodUiBadUxComponent', () => {
  let component: RandomFormGoodUiBadUxComponent;
  let fixture: ComponentFixture<RandomFormGoodUiBadUxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RandomFormGoodUiBadUxComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RandomFormGoodUiBadUxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
