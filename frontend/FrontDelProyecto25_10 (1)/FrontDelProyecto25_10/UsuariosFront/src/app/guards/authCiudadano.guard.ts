import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { EstadoService } from '../core/estado.service'; 

@Injectable({
  providedIn: 'root'
})
export class AuthCiudadanoGuard implements CanActivate {

  constructor(
    private estadoService: EstadoService,
    private router: Router
  ) {}

  canActivate(): boolean {
    const tokenVoto = this.estadoService.getTokenDeVoto();

    if (tokenVoto) {
      return true; 
    } else {
      this.router.navigate(['/home']);
      return false;
    }
  }
}