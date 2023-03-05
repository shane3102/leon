import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormPanelRepresentationComponent } from './form-panel-representation.component';

describe('FormPanelRepresentationComponent', () => {
  let component: FormPanelRepresentationComponent;
  let fixture: ComponentFixture<FormPanelRepresentationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormPanelRepresentationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormPanelRepresentationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
