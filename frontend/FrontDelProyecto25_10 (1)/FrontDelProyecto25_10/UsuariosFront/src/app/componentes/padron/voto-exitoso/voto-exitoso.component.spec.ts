import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VotoExitosoComponent } from './voto-exitoso.component';

describe('VotoExitosoComponent', () => {
  let component: VotoExitosoComponent;
  let fixture: ComponentFixture<VotoExitosoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VotoExitosoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VotoExitosoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
