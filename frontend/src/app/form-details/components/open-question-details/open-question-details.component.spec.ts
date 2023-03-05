import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OpenQuestionDetailsComponent } from './open-question-details.component';

describe('OpenQuestionDetailsComponent', () => {
  let component: OpenQuestionDetailsComponent;
  let fixture: ComponentFixture<OpenQuestionDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OpenQuestionDetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OpenQuestionDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
