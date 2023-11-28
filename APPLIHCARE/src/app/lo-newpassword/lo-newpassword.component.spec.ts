import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoNewpasswordComponent } from './lo-newpassword.component';

describe('LoNewpasswordComponent', () => {
  let component: LoNewpasswordComponent;
  let fixture: ComponentFixture<LoNewpasswordComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LoNewpasswordComponent]
    });
    fixture = TestBed.createComponent(LoNewpasswordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
