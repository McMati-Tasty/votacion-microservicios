import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';
import { Ciudadano } from 'src/app/modelo/Ciudadano';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-listado-ciudadano',
  templateUrl: './ListadoCiudadano.component.html',
  styleUrls: ['./ListadoCiudadano.component.css']
})
export class ListadoCiudadanoComponent implements OnInit {
  ciudadanos: Ciudadano[] = [];
  error: string = '';

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.cargarCiudadanos();
  }

  cargarCiudadanos() {
    const token = localStorage.getItem('TokenUsuarios');
    if (!token) {
      this.mostrarError('No hay sesión activa. Iniciá sesión como administrador.');
      return;
    }

    const headers = new HttpHeaders({ 'Authorization': token });

    this.http.get<Ciudadano[]>('http://localhost:8090/bff/padron/listado', { headers })
      .subscribe({
        next: data => {
          this.ciudadanos = data;
          if (!data.length) this.mostrarInfo('No hay ciudadanos registrados.');
        },
        error: err => this.mostrarError('No se pudieron cargar los ciudadanos. Asegurate de estar logueado como admin.')
      });
  }

  private mostrarError(msg: string) {
    this.error = msg;
    Swal.fire({ icon: 'error', title: 'Error', text: msg });
  }

  private mostrarInfo(msg: string) {
    Swal.fire({ icon: 'info', title: 'Información', text: msg });
  }
}