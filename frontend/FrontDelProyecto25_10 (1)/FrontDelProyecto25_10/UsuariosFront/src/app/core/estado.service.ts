import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EstadoService {

  private votoToken = new BehaviorSubject<string | null>(null);
  
  private dniCiudadano: string | null = null; 

  
  setTokenDeVoto(token: string) {
    this.votoToken.next(token);
    sessionStorage.setItem('voto_token', token);
    
    this.limpiarDniCiudadano();
  }


  getTokenDeVoto(): string | null {
    if (this.votoToken.getValue()) {
      return this.votoToken.getValue();
    }
    return sessionStorage.getItem('voto_token');
  }
  
  setDniCiudadano(dni: string) {
    this.dniCiudadano = dni;
  }

  getDniCiudadano(): string | null {
    return this.dniCiudadano;
  }

  limpiarDniCiudadano() {
    this.dniCiudadano = null;
  }

  
  limpiarEstado() {
    this.votoToken.next(null);
    sessionStorage.removeItem('voto_token');
    this.limpiarDniCiudadano(); 
  }
}