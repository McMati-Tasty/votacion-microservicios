import { Component, OnInit } from '@angular/core';
import { Chart, registerables } from 'chart.js';
import Swal from 'sweetalert2';
import { Observable } from 'rxjs'; 
import { ResultadoCargo } from '../../modelo/ResultadoCargo';
import { OpcionAdminService } from '../../service/opcionAdmin.service';

Chart.register(...registerables);

@Component({
  selector: 'app-resumen-votos',
  templateUrl: './ResumenVotos.component.html',
  styleUrls: ['./ResumenVotos.component.css']
})
export class ResumenVotosComponent implements OnInit {

  resultados: ResultadoCargo[] = [];
  rolSeleccionado: string = 'presidente'; 
  grafico: any;

  constructor(private adminService: OpcionAdminService) { }

  ngOnInit(): void {
    this.cargarResumen(this.rolSeleccionado);
  }

  cambioRol(nuevoRol: string) {
    this.rolSeleccionado = nuevoRol;
    this.cargarResumen(nuevoRol);
  }

  cargarResumen(rol: string) {
    
    let observableResumen: Observable<ResultadoCargo[]>;

    switch (rol) {
      case 'presidente':
        observableResumen = this.adminService.getResumenPresidente();
        break;
      case 'vicepresidente':
        observableResumen = this.adminService.getResumenVicepresidente();
        break;
      case 'gobernador':
        observableResumen = this.adminService.getResumenGobernador();
        break;
      default:
        this.mostrarError('Rol no válido seleccionado.');
        return;
    }

    observableResumen.subscribe({
      next: data => {
        this.resultados = data;
        if (data.length === 0) {
          this.mostrarInfo('No hay resultados para este cargo.');
        }
        this.crearGrafico();
      },
      error: err => {
        console.error('Error cargando resumen de votos:', err);
        this.mostrarError('No se pudo cargar el resumen de votos.');
      }
    });
  }

  crearGrafico() {
    if (this.grafico) this.grafico.destroy();

    this.grafico = new Chart('graficoCircular', { 
      type: 'pie',
      data: {
       labels: this.resultados.map(r => r.partido),
        datasets: [{
          label: 'Votos',
          data: this.resultados.map(r => r.totalVotos),
          backgroundColor: ['#36A2EB', '#FF6384', '#4BC0C0', '#FFCE56', '#9966FF'],
          borderWidth: 1
        }]
      },
      options: {
        responsive: true,
        plugins: {
          legend: { position: 'bottom' }
        }
      }
    });
  }

  private mostrarError(msg: string) {
    Swal.fire({ icon: 'error', title: 'Error', text: msg });
  }

  private mostrarInfo(msg: string) {
    Swal.fire({ icon: 'info', title: 'Información', text: msg });
  }
}