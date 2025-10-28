import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VotoBloqueadoComponent } from './voto-bloqueado.component';

describe('VotoBloqueadoComponent', () => {
  let component: VotoBloqueadoComponent;
  let fixture: ComponentFixture<VotoBloqueadoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VotoBloqueadoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VotoBloqueadoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
