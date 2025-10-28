import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { OpcionAdminService, UserDto, ResponseLoginDto } from '../../service/opcionAdmin.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    
    private adminService: OpcionAdminService, 
    private router: Router
  ) {}

  ngOnInit(): void {
    this.initializeForm();
  }

  
  initializeForm() {
    this.loginForm = this.formBuilder.group({
      // 3. CORRECCIÓN: El campo se llama 'email' (basado en 'admin@web.com')
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]]
    });
  }


  emailNoValido() {
    return (
      (this.loginForm.get('email')?.dirty || this.loginForm.get('email')?.touched) &&
      this.loginForm.get('email')?.invalid
    );
  }

 
  passwordNoValido() {
    return (
      (this.loginForm.get('password')?.dirty || this.loginForm.get('password')?.touched) &&
      this.loginForm.get('password')?.invalid
    );
  }

  
  volverHome() {
    this.router.navigateByUrl('/home');
  }

  
  login() {
    if (this.loginForm.invalid) {
      
      Swal.fire({
        icon: 'warning',
        title: 'Campos incompletos',
        text: 'Por favor, completá tu email y contraseña.',
        confirmButtonText: 'Entendido',
      });
      return;
    }

    const payload: UserDto = {
      email: this.loginForm.controls['email'].value,
      password: this.loginForm.controls['password'].value
    };

    Swal.fire({
      title: '¿Confirmar inicio de sesión?',
      text: `¿Querés iniciar sesión como "${payload.email}"?`, // <-- CORREGIDO
      icon: 'question',
      showCancelButton: true,
      confirmButtonText: 'Sí, ingresar',
      cancelButtonText: 'Cancelar',
    }).then((result) => {
      if (result.isConfirmed) {
        
        this.adminService.login(payload).subscribe({
          next: (response: ResponseLoginDto) => {
            
            const token = response.token.startsWith('Bearer ') ? response.token : `Bearer ${response.token}`;
            localStorage.setItem('TokenAdmin', token); 

            Swal.fire({
              icon: 'success',
              title: '¡Éxito!',
              text: 'Inicio de sesión correcto',
              timer: 1800,
              showConfirmButton: false
            }).then(() => {
              this.router.navigate(['/admin']); 
            });
          },
          error: (err) => {
            let msg = 'Ocurrió un error al iniciar sesión.';
            if (err.error && err.error.mensaje) msg = err.error.mensaje;

            Swal.fire({
              icon: 'error',
              title: 'Error',
              text: msg,
              confirmButtonText: 'Entendido',
            });
          }
        });
      }
    });
  }
}