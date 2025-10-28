import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { MenuAdminComponent } from './MenuAdmin/MenuAdmin.component';
import { CandidatoListadoComponent } from './CandidatoListado/CandidatoListado.component';
import { ListadoCiudadanoComponent } from './ListadoCiudadano/ListadoCiudadano.component';
import { ResumenVotosComponent } from './ResumenVotos/ResumenVotos.component';
import { SeguridadComponent } from './MenuAdmin/seguridad/seguridad.component';
import { FaqComponent } from './MenuAdmin/FAQ/faq.component';
import { AdminGuard } from '../guards/admin.guard'; 



const routes: Routes = [
  
  
  { path: 'login', component: LoginComponent },

  
  { 
    path: '', 
    component: MenuAdminComponent, 
    canActivate: [AdminGuard], 
    pathMatch: 'full'
  },

 

  { 
    path: 'votacion/resumen', 
    component: ResumenVotosComponent,
    canActivate: [AdminGuard] 
  },
  
  // Ruta: /admin/candidato/listado
  { 
    path: 'candidato/listado', 
    component: CandidatoListadoComponent,
    canActivate: [AdminGuard] 
  },
  
  // Ruta: /admin/ciudadano/listado
  { 
    path: 'ciudadano/listado', 
    component: ListadoCiudadanoComponent,
    canActivate: [AdminGuard] 
  },
  
  // Ruta: /admin/seguridad
  { 
    path: 'seguridad', 
    component: SeguridadComponent,
    canActivate: [AdminGuard] 
  },
  
  // Ruta: /admin/faq
  { 
    path: 'faq', 
    component: FaqComponent,
    canActivate: [AdminGuard] 
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }