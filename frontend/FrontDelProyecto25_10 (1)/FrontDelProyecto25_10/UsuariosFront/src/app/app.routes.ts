import { Routes } from '@angular/router';

import { HomeComponent } from './componentes/commons/home/home.component';

import { LoginCiudadanoComponent } from './componentes/padron/LoginCiudadano/LoginCiudadano.component';
import { IngresarTokenComponent } from './componentes/padron/ingresar-token/ingresar-token.component';
import { SeleccionarVotoComponent } from './componentes/padron/seleccionar-voto/seleccionar-voto.component';
import { VotoExitosoComponent } from './componentes/padron/voto-exitoso/voto-exitoso.component';
import { VotoBloqueadoComponent } from './componentes/padron/voto-bloqueado/voto-bloqueado.component';

import { AuthCiudadanoGuard } from './guards/authCiudadano.guard';

export const routes: Routes = [

  { path: 'home', component: HomeComponent },

  { path: 'LoginCiudadano', component: LoginCiudadanoComponent },
  { path: 'ingresar-token', component: IngresarTokenComponent },
  { path: 'seleccionar-voto', component: SeleccionarVotoComponent, canActivate: [AuthCiudadanoGuard] },
  { path: 'voto-exitoso', component: VotoExitosoComponent },
  { path: 'voto-bloqueado', component: VotoBloqueadoComponent },

  { 
    path: 'admin', 
    loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule) 
  },

  
  { path: '', redirectTo: 'home', pathMatch: 'full' }, 
  
  { path: '**', redirectTo: 'home', pathMatch: 'full' } 
];