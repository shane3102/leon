import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DropdownBadUiBadUxComponent } from './dropdown-bad-ui-bad-ux.component';

describe('DropdownBadUiBadUxComponent', () => {
  let component: DropdownBadUiBadUxComponent;
  let fixture: ComponentFixture<DropdownBadUiBadUxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DropdownBadUiBadUxComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DropdownBadUiBadUxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
