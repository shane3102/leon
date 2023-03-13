import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormRandomDownloadCsvComponent } from './form-random-download-csv.component';

describe('FormRandomDownloadCsvComponent', () => {
  let component: FormRandomDownloadCsvComponent;
  let fixture: ComponentFixture<FormRandomDownloadCsvComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormRandomDownloadCsvComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormRandomDownloadCsvComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
