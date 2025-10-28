import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';
import { Candidato } from '../../modelo/Candidato';
import { OpcionAdminService } from '../../service/opcionAdmin.service';

@Component({
  selector: 'app-candidato-listado',
  templateUrl: './CandidatoListado.component.html',
  styleUrls: ['./CandidatoListado.component.css']
})
export class CandidatoListadoComponent implements OnInit {
  candidatos: Candidato[] = [];
  error: string = '';

  // 2. Inyectamos el service
  constructor(private adminService: OpcionAdminService) {}

  ngOnInit(): void {
    this.cargarCandidatos();
  }

  cargarCandidatos() {
    // 3. Usamos el service
    this.adminService.getListadoCandidatos().subscribe({
      next: data => {
        this.candidatos = data;
        if (!data.length) this.mostrarInfo('No hay candidatos cargados.');
      },
      error: err => this.mostrarError('No se pudieron cargar los candidatos. Asegurate de estar logueado como admin.')
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