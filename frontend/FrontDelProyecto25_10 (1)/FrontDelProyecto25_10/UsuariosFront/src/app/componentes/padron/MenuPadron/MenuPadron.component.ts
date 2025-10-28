import { Component } from '@angular/core';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-MenuPadron',
  templateUrl: './MenuPadron.component.html',
  styleUrls: ['./MenuPadron.component.css']
})
export class MenuPadronComponent {

  constructor(private router: Router) {}

  logout() {
    localStorage.removeItem('TokenCiudadano'); 
    Swal.fire({
      icon: 'success',
      title: '¡Sesión cerrada!',
      text: 'Has salido correctamente del sistema.',
      showConfirmButton: false,
      timer: 1500
    }).then(() => {
      this.router.navigateByUrl('/login-ciudadano'); 
    });
  }
}