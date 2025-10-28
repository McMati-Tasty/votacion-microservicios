import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http'; 
import { RouterModule } from '@angular/router'; 
import { ReactiveFormsModule } from '@angular/forms'; 

import { routes } from './app.routes'; 
import { AppComponent } from './app.component';
import { HomeComponent } from './componentes/commons/home/home.component';
import { LoginCiudadanoComponent } from './componentes/padron/LoginCiudadano/LoginCiudadano.component';
import { IngresarTokenComponent } from './componentes/padron/ingresar-token/ingresar-token.component';
import { SeleccionarVotoComponent } from './componentes/padron/seleccionar-voto/seleccionar-voto.component';
import { VotoExitosoComponent } from './componentes/padron/voto-exitoso/voto-exitoso.component';
import { VotoBloqueadoComponent } from './componentes/padron/voto-bloqueado/voto-bloqueado.component';

import { AdminInterceptor } from './core/admin.interceptor'; 

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginCiudadanoComponent,
    IngresarTokenComponent,
    SeleccionarVotoComponent,
    VotoExitosoComponent,
    VotoBloqueadoComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule, 
    ReactiveFormsModule, 
    RouterModule.forRoot(routes) 
  ],
  
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AdminInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }