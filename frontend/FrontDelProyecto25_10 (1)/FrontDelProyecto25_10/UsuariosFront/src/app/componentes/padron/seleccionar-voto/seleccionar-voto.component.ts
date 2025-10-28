// src/app/componentes/padron/seleccionar-voto/seleccionar-voto.component.ts

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CandidatoDTO } from '../../../modelo/padron.modelos';
import { CiudadanoService } from '../../../service/ciudadano.service';
import { EstadoService } from '../../../core/estado.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-seleccionar-voto',
  templateUrl: './seleccionar-voto.component.html',
  styleUrls: ['./seleccionar-voto.component.css']
})
export class SeleccionarVotoComponent implements OnInit {

  cargandoCandidatos: boolean = true;
  cargandoVoto: boolean = false;
  errorMensaje: string | null = null;
  listaCompletaCandidatos: CandidatoDTO[] = [];
  votoForm: FormGroup;

  constructor(
    private ciudadanoService: CiudadanoService,
    private estadoService: EstadoService,
    private router: Router,
    private fb: FormBuilder
  ) {
    this.votoForm = this.fb.group({
      idPresidente: [null],
      idVicepresidente: [null],
      idGobernador: [null]
    });
  }

  ngOnInit(): void {
    this.cargarCandidatos();
  }


  cargarCandidatos() {
    this.cargandoCandidatos = true;
    this.errorMensaje = null;
    
    this.ciudadanoService.obtenerCandidatos().subscribe({
      next: (data) => {
        this.listaCompletaCandidatos = data;
        this.cargandoCandidatos = false;
      },
      error: (err) => {
        console.error('Error al cargar candidatos:', err);
        this.errorMensaje = 'No se pudieron cargar los candidatos. Intente más tarde.';
        this.cargandoCandidatos = false;
      }
    });
  }

  get presidentes(): CandidatoDTO[] {
    return this.listaCompletaCandidatos.filter(c => c.rol === 'PRESIDENTE');
  }
  get vicepresidentes(): CandidatoDTO[] {
    return this.listaCompletaCandidatos.filter(c => c.rol === 'VICEPRESIDENTE');
  }
  get gobernadores(): CandidatoDTO[] {
    return this.listaCompletaCandidatos.filter(c => c.rol === 'GOBERNADOR');
  }


  seleccionarCandidato(controlName: string, id: number) {
    const control = this.votoForm.get(controlName);
    
    if (control?.value === id) {
      control?.setValue(null); 
    } else {
      control?.setValue(id);
    }
  }

 
  confirmarVoto() {
    this.cargandoVoto = true;
    this.errorMensaje = null;

    const votosSeleccionados = this.votoForm.value;

    this.ciudadanoService.registrarVoto(votosSeleccionados).subscribe({
      next: (respuesta) => {
        this.cargandoVoto = false;
        
        this.router.navigate(['/voto-exitoso']) 
           .then(() => {
                this.estadoService.limpiarEstado(); 
           });
      },
      error: (err) => {
        this.cargandoVoto = false;
        this.estadoService.limpiarEstado();
        this.router.navigate(['/voto-bloqueado']); 
      }
    });
  }
}