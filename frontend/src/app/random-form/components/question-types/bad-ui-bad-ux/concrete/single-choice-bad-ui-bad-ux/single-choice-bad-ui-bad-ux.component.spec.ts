import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SingleChoiceBadUiBadUxComponent } from './single-choice-bad-ui-bad-ux.component';

describe('SingleChoiceBadUiBadUxComponent', () => {
  let component: SingleChoiceBadUiBadUxComponent;
  let fixture: ComponentFixture<SingleChoiceBadUiBadUxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SingleChoiceBadUiBadUxComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SingleChoiceBadUiBadUxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
