import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CiudadanoService } from '../../../service/ciudadano.service'; 
import { EstadoService } from '../../../core/estado.service'; 

@Component({
  selector: 'app-login-ciudadano',
  templateUrl: './LoginCiudadano.component.html',
  styleUrls: ['./LoginCiudadano.component.css']
})
export class LoginCiudadanoComponent implements OnInit {

  loginForm: FormGroup;
  errorMensaje: string | null = null;
  cargando: boolean = false;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private ciudadanoService: CiudadanoService, 
    private estadoService: EstadoService 
  ) {
    this.loginForm = this.fb.group({
      dni: ['', [Validators.required, Validators.pattern('^[0-9]{8}$')]] 
    });
  }

  ngOnInit(): void {
    this.estadoService.limpiarEstado();
  }

  ingresar() {
    if (this.loginForm.invalid) {
      this.loginForm.markAllAsTouched(); 
      return;
    }

    this.cargando = true;
    this.errorMensaje = null;
    const dni = this.loginForm.value.dni;

    this.ciudadanoService.solicitarToken(dni).subscribe({

      next: (respuesta) => {
        this.cargando = false;
        console.log('Login exitoso. Email de verificación enviado.');

       
        this.estadoService.setDniCiudadano(dni);

        this.router.navigate(['/ingresar-token']);
      },

      error: (error) => {
        this.cargando = false;
        console.error('Error al solicitar token:', error);

        this.errorMensaje = 'DNI no encontrado o no habilitado para votar.'; 
      }
    });
  }

  volver() {
    this.router.navigate(['/home']);
  }
}