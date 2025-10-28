import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-voto-bloqueado',
  templateUrl: './voto-bloqueado.component.html',
  styleUrls: ['./voto-bloqueado.component.css']
})
export class VotoBloqueadoComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  salir() {
    this.router.navigate(['/home']);
  }
}