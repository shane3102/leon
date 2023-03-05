import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DropdownBadUiGoodUxComponent } from './dropdown-bad-ui-good-ux.component';

describe('DropdownBadUiGoodUxComponent', () => {
  let component: DropdownBadUiGoodUxComponent;
  let fixture: ComponentFixture<DropdownBadUiGoodUxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DropdownBadUiGoodUxComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DropdownBadUiGoodUxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
