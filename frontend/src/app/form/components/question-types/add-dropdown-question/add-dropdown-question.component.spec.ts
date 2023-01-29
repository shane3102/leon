import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddDropdownQuestionComponent } from './add-dropdown-question.component';

describe('AddDropdownQuestionComponent', () => {
  let component: AddDropdownQuestionComponent;
  let fixture: ComponentFixture<AddDropdownQuestionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddDropdownQuestionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddDropdownQuestionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
