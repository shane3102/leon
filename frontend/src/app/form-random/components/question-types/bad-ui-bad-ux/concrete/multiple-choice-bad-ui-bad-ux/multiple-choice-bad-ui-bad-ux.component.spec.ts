import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MultipleChoiceBadUiBadUxComponent } from './multiple-choice-bad-ui-bad-ux.component';

describe('MultipleChoiceBadUiBadUxComponent', () => {
  let component: MultipleChoiceBadUiBadUxComponent;
  let fixture: ComponentFixture<MultipleChoiceBadUiBadUxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MultipleChoiceBadUiBadUxComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MultipleChoiceBadUiBadUxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
