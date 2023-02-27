import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SurveyOnRandomFormsComponent } from './survey-on-random-forms.component';

describe('SurveyOnRandomFormsComponent', () => {
  let component: SurveyOnRandomFormsComponent;
  let fixture: ComponentFixture<SurveyOnRandomFormsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SurveyOnRandomFormsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SurveyOnRandomFormsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
