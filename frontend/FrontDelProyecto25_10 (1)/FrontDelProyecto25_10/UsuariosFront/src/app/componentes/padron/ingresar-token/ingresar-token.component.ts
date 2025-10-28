import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { EstadoService } from '../../../core/estado.service'; 
import { CiudadanoService } from '../../../service/ciudadano.service'; 
import { CiudadanoLoginResponse } from '../../../modelo/padron.modelos'; 

@Component({
  selector: 'app-ingresar-token',
  templateUrl: './ingresar-token.component.html',
  styleUrls: ['./ingresar-token.component.css']
})
export class IngresarTokenComponent implements OnInit {

  tokenForm: FormGroup;
  errorMensaje: string | null = null;
  cargando: boolean = false;
  dniEnProceso: string = '';

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private estadoService: EstadoService, 
    private ciudadanoService: CiudadanoService
  ) {
    this.tokenForm = this.fb.group({
      token: ['', [Validators.required, Validators.minLength(10)]] 
    });
  }

  ngOnInit(): void {
    this.dniEnProceso = this.estadoService.getDniCiudadano(); 
    if (!this.dniEnProceso) {
      this.router.navigate(['/LoginCiudadano']);
    }
  }

  continuar() {
    if (this.tokenForm.invalid || !this.dniEnProceso) {
      this.tokenForm.markAllAsTouched();
      this.errorMensaje = 'Por favor, ingrese un código válido.';
      return;
    }

    this.cargando = true;
    this.errorMensaje = null;
    const tokenIngresado = this.tokenForm.value.token.trim();

    this.ciudadanoService.verificarToken(this.dniEnProceso, tokenIngresado).subscribe({
      
      next: (respuesta: CiudadanoLoginResponse) => {
        this.cargando = false;
        
        if (respuesta.tokenDeVoto) {
          this.estadoService.setTokenDeVoto(respuesta.tokenDeVoto);
          
          this.router.navigate(['/seleccionar-voto']); 
        } else {
          this.errorMensaje = 'Error de seguridad al obtener token. Intente de nuevo.';
      }
      },
      error: (error) => {
        this.cargando = false;
        if (error.status === 401) {
            this.errorMensaje = 'Código incorrecto o sesión expirada.';
        } else {
            this.errorMensaje = 'Error al verificar su identidad. Intente nuevamente.';
        }
      }
    });
  }

  volver() {
    this.router.navigate(['/LoginCiudadano']);
  }
}