import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DroitListComponent } from './droit-list.component';

describe('DroitListComponent', () => {
  let component: DroitListComponent;
  let fixture: ComponentFixture<DroitListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DroitListComponent]
    });
    fixture = TestBed.createComponent(DroitListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
