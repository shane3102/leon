import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DropdownGoodUiGoodUxComponent } from './dropdown-good-ui-good-ux.component';

describe('DropdownGoodUiGoodUxComponent', () => {
  let component: DropdownGoodUiGoodUxComponent;
  let fixture: ComponentFixture<DropdownGoodUiGoodUxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DropdownGoodUiGoodUxComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DropdownGoodUiGoodUxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
