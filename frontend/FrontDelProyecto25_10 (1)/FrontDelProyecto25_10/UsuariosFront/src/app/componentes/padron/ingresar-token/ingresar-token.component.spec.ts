import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IngresarTokenComponent } from './ingresar-token.component';

describe('IngresarTokenComponent', () => {
  let component: IngresarTokenComponent;
  let fixture: ComponentFixture<IngresarTokenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IngresarTokenComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IngresarTokenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
