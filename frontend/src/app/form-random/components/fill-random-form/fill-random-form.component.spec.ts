import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FillRandomFormComponent } from './fill-random-form.component';

describe('FillRandomFormComponent', () => {
  let component: FillRandomFormComponent;
  let fixture: ComponentFixture<FillRandomFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FillRandomFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FillRandomFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
