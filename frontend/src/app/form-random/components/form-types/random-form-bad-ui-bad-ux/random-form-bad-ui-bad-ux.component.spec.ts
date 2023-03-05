import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RandomFormBadUiBadUxComponent } from './random-form-bad-ui-bad-ux.component';

describe('RandomFormBadUiBadUxComponent', () => {
  let component: RandomFormBadUiBadUxComponent;
  let fixture: ComponentFixture<RandomFormBadUiBadUxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RandomFormBadUiBadUxComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RandomFormBadUiBadUxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
