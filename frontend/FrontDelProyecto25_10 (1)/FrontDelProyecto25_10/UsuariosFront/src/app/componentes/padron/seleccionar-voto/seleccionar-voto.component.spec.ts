import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SeleccionarVotoComponent } from './seleccionar-voto.component';

describe('SeleccionarVotoComponent', () => {
  let component: SeleccionarVotoComponent;
  let fixture: ComponentFixture<SeleccionarVotoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SeleccionarVotoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SeleccionarVotoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
