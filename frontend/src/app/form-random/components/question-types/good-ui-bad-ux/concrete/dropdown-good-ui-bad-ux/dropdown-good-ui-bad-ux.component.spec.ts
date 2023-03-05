import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DropdownGoodUiBadUxComponent } from './dropdown-good-ui-bad-ux.component';

describe('DropdownGoodUiBadUxComponent', () => {
  let component: DropdownGoodUiBadUxComponent;
  let fixture: ComponentFixture<DropdownGoodUiBadUxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DropdownGoodUiBadUxComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DropdownGoodUiBadUxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
