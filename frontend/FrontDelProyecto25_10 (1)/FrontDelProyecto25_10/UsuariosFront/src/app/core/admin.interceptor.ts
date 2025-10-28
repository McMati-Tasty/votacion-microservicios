import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class AdminInterceptor implements HttpInterceptor {

  constructor() {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    
    const token = localStorage.getItem('TokenAdmin');

    if (token && !request.url.includes('/usuarios/login')) {
      
      
      const cloned = request.clone({
        setHeaders: {
          Authorization: token 
        }
      });

      return next.handle(cloned);
    }

    return next.handle(request);
  }
}