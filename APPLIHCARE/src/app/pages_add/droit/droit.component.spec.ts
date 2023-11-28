import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DroitComponent } from './droit.component';

describe('DroitComponent', () => {
  let component: DroitComponent;
  let fixture: ComponentFixture<DroitComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DroitComponent]
    });
    fixture = TestBed.createComponent(DroitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
