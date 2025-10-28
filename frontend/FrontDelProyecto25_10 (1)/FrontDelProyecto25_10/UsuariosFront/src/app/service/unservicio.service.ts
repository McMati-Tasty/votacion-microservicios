import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
 import { map , delay } from 'rxjs/operators';
import { loginResponse } from '../modelo/loginResponse';
import { loginRequest } from '../modelo/loginRequest';


@Injectable({
  providedIn: 'root'
})
export class UnservicioService {

  constructor(public http: HttpClient) { }


  private urlUsuarios = 'http://localhost:8084/usuarios';


 login( loginRequest : loginRequest): Observable<loginResponse> {

 const headers = new HttpHeaders();


return this.http.post("http://localhost:8084/usuarios/login"  , loginRequest )
    .pipe(
      map( (resp:loginResponse) => {
            return resp ;
        }
      )
    )
 }


}
