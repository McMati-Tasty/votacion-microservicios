import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router'; 
import Swal from 'sweetalert2';
import { OpcionAdminService } from '../../service/opcionAdmin.service';
import { OpcionAdmin } from '../../modelo/opcionAdmin';

@Component({
  selector: 'app-MenuAdmin',
  templateUrl: './MenuAdmin.component.html',
  styleUrls: ['./MenuAdmin.component.css']
})
export class MenuAdminComponent implements OnInit {

  opciones: OpcionAdmin[] = [];

  constructor(
    private router: Router, 
    private route: ActivatedRoute, 
    private adminService: OpcionAdminService
  ) {}

  ngOnInit(): void {
    this.cargarOpciones();
  }

  cargarOpciones() {
    this.adminService.getOpcionesAdmin().subscribe({
      next: data => this.opciones = data,
      error: err => {
        console.error(err);
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'No pudimos cargar las opciones admin'
        }); 
      }
    });
  }

  
  
  irA(opcion: OpcionAdmin) {
    let ruta = '';

    
    switch (opcion.titulo.toLowerCase()) {
      case 'candidatos':
       
        ruta = 'candidato/listado'; 
        break;
      case 'votos':
        ruta = 'votacion/resumen';
        break;
      case 'ciudadanos':
        ruta = 'ciudadano/listado';
        break;
      default:
        console.warn('Ruta no definida para:', opcion.titulo);
        return; 
    }

    this.router.navigate([ruta], { relativeTo: this.route });
  }
  

  logout() {
    Swal.fire({
      title: 'Cerrar sesión',
      text: '¿Deseas cerrar sesión ahora?',
      icon: 'question',
      showCancelButton: true,
      confirmButtonText: 'Sí, cerrar sesión',
      cancelButtonText: 'Cancelar',
      reverseButtons: true
    }).then((result) => {
      if (result.isConfirmed) {
        localStorage.removeItem('TokenAdmin'); 
        Swal.fire({
          icon: 'success',
          title: 'Sesión cerrada',
          text: 'Has cerrado sesión correctamente.',
          timer: 1500,
          showConfirmButton: false,
          willClose: () => this.router.navigate(['/home'])
        });
      }
    });
  }
}