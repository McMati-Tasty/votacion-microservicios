import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { OpcionAdmin } from '../modelo/opcionAdmin';
import { Ciudadano } from '../modelo/Ciudadano'; 
import { ResultadoCargo } from '../modelo/ResultadoCargo'; 
import { Candidato } from '../modelo/Candidato'; 


export interface UserDto {
  email: string; // 
  password: string; 
}

export interface ResponseLoginDto {
  token: string;
  mensaje: string;
}

@Injectable({
  providedIn: 'root'
})
export class OpcionAdminService {

  
  private BFF_URL = 'http://localhost:8090'; 

  constructor(private http: HttpClient) {}

  private getAuthHeaders(): HttpHeaders {
    const token = localStorage.getItem('TokenAdmin') || ''; 
    return new HttpHeaders().set('Authorization', `Bearer ${token}`);
  }


  login(data: UserDto): Observable<ResponseLoginDto> {
   
    const url = `${this.BFF_URL}/usuarios/login`; 
    return this.http.post<ResponseLoginDto>(url, data);
  }

  logout(): void {
    localStorage.removeItem('TokenAdmin');
  }


  getResumenPresidente(): Observable<ResultadoCargo[]> {
    const url = `${this.BFF_URL}/bff/votacion/resumen/presidente`;
    return this.http.get<ResultadoCargo[]>(url, { headers: this.getAuthHeaders() });
  }
  getResumenVicepresidente(): Observable<ResultadoCargo[]> {
    const url = `${this.BFF_URL}/bff/votacion/resumen/vicepresidente`;
    return this.http.get<ResultadoCargo[]>(url, { headers: this.getAuthHeaders() });
  }
  getResumenGobernador(): Observable<ResultadoCargo[]> {
    const url = `${this.BFF_URL}/bff/votacion/resumen/gobernador`;
    return this.http.get<ResultadoCargo[]>(url, { headers: this.getAuthHeaders() });
  }
  
  getListadoCiudadanos(): Observable<Ciudadano[]> {
    const url = `${this.BFF_URL}/bff/padron/listado`;
    return this.http.get<Ciudadano[]>(url, { headers: this.getAuthHeaders() });
  }

  getListadoCandidatos(): Observable<Candidato[]> {
    const url = `${this.BFF_URL}/bff/candidato/listado`;
    return this.http.get<Candidato[]>(url, { headers: this.getAuthHeaders() });
  }
  
  agregarCandidato(candidato: Candidato): Observable<Candidato> {
    const url = `${this.BFF_URL}/bff/candidato/agregar`;
    return this.http.post<Candidato>(url, candidato, { headers: this.getAuthHeaders() });
  }

  eliminarCandidato(id: number): Observable<void> {
    const url = `${this.BFF_URL}/bff/candidato/eliminar/${id}`;
    return this.http.delete<void>(url, { headers: this.getAuthHeaders() });
  }

getOpcionesAdmin(): Observable<OpcionAdmin[]> {
    
    const url = `${this.BFF_URL}/api/opciones-admin`; 
    return this.http.get<OpcionAdmin[]>(url, { headers: this.getAuthHeaders() });
  }

}