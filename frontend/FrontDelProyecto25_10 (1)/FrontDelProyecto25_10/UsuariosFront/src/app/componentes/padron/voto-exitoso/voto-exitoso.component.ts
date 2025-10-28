import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-voto-exitoso',
  templateUrl: './voto-exitoso.component.html',
  styleUrls: ['./voto-exitoso.component.css']
})
export class VotoExitosoComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  salir() {
    // Lo mandamos al Home
    this.router.navigate(['/home']);
  }
}