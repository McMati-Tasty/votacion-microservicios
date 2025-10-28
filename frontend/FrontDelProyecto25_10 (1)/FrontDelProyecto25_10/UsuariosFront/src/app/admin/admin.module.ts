import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ReactiveFormsModule, FormsModule } from '@angular/forms'; 

import { AdminRoutingModule } from './admin-routing.module';

import { LoginComponent } from './login/login.component';
import { MenuAdminComponent } from './MenuAdmin/MenuAdmin.component';
import { CandidatoListadoComponent } from './CandidatoListado/CandidatoListado.component';
import { ListadoCiudadanoComponent } from './ListadoCiudadano/ListadoCiudadano.component';
import { ResumenVotosComponent } from './ResumenVotos/ResumenVotos.component';

import { SeguridadComponent } from './MenuAdmin/seguridad/seguridad.component';
import { FaqComponent } from './MenuAdmin/FAQ/faq.component';


@NgModule({
  declarations: [
    LoginComponent,
    MenuAdminComponent,
    CandidatoListadoComponent,
    ListadoCiudadanoComponent,
    ResumenVotosComponent,
    SeguridadComponent,
    FaqComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule, 
    FormsModule, 
    ReactiveFormsModule 
  ]
})
export class AdminModule { }