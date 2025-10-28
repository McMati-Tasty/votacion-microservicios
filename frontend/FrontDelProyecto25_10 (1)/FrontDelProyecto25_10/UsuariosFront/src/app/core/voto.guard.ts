import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { EstadoService } from './estado.service';

@Injectable({
  providedIn: 'root'
})
export class VotoGuard implements CanActivate {

  constructor(
    private estadoService: EstadoService,
    private router: Router
  ) {}

  canActivate(): boolean {

    if (this.estadoService.getTokenDeVoto()) {
      return true;
    }

    console.warn('Acceso denegado. No hay token de voto.');
    this.router.navigate(['/login']);
    return false;
  }
}