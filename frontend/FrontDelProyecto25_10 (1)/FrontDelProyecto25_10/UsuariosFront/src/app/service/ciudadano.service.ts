import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';


import { 
  CandidatoDTO, 
  CiudadanoLoginRequest, 
  CiudadanoLoginResponse, 
  RegistrarVotoRequest, 
  RegistrarVotoResponse 
} from '../modelo/padron.modelos';

import { EstadoService } from '../core/estado.service';

@Injectable({
  providedIn: 'root'
})
export class CiudadanoService {

  private BFF_URL = 'http://localhost:8090/bff';

  constructor(
    private http: HttpClient,
    private estadoService: EstadoService
  ) { }

  solicitarToken(dni: string): Observable<CiudadanoLoginResponse> {
    const url = `${this.BFF_URL}/padron/login`;
    const payload: CiudadanoLoginRequest = { dni: dni }; 
    return this.http.post<CiudadanoLoginResponse>(url, payload).pipe(
      catchError(this.handleError)
    );
  }

  
  verificarToken(dni: string, token: string): Observable<CiudadanoLoginResponse> {

    const url = `${this.BFF_URL}/padron/verificarToken`; 
    const payload = {
      dni: dni,
      token: token 
    };
    return this.http.post<CiudadanoLoginResponse>(url, payload).pipe(
      catchError(this.handleError)
    );
  }

  
  obtenerCandidatos(): Observable<CandidatoDTO[]> {
    const url = `${this.BFF_URL}/candidato/listado`; 
    return this.http.get<CandidatoDTO[]>(url).pipe(
      catchError(this.handleError)
    );
  }


  registrarVoto(votos: { idPresidente: number | null, idVicepresidente: number | null, idGobernador: number | null }): Observable<RegistrarVotoResponse> {
    
    const token = this.estadoService.getTokenDeVoto();
    if (!token) {
      return throwError(() => new Error('Error fatal: No hay token de voto.'));
    }

    const VOTACION_URL = 'http://localhost:8086/votos/votar'; 
    const url = VOTACION_URL;
    
    const payload: RegistrarVotoRequest = {
      tokenDeVoto: token,
      idPresidente: votos.idPresidente,
      idVicepresidente: votos.idVicepresidente,
      idGobernador: votos.idGobernador
    };

    return this.http.post<RegistrarVotoResponse>(url, payload).pipe(
      catchError(this.handleError)
    );
  }

 
  private handleError(error: HttpErrorResponse) {
    console.error('Ocurrió un error en el servicio:', error.message);
    return throwError(() => new Error('Error en la comunicación con el servidor.'));
  }
}
