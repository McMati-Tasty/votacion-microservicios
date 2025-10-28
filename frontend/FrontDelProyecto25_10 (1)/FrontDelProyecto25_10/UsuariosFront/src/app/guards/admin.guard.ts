import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AdminGuard implements CanActivate {

  constructor(private router: Router) {} 
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean {
    

    const token = localStorage.getItem('TokenAdmin');

    if (token) {
      return true; 
    }

    console.log('AdminGuard: No hay token, redirigiendo a /admin/login');
    this.router.navigate(['/admin/login']);
    return false;
  }
}