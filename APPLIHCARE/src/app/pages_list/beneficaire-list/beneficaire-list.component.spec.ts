import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BeneficaireListComponent } from './beneficaire-list.component';

describe('BeneficaireListComponent', () => {
  let component: BeneficaireListComponent;
  let fixture: ComponentFixture<BeneficaireListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BeneficaireListComponent]
    });
    fixture = TestBed.createComponent(BeneficaireListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
