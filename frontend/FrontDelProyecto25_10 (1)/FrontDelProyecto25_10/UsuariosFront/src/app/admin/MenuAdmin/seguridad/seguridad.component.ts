import { Component } from '@angular/core';

@Component({
  selector: 'app-seguridad',
  templateUrl: './seguridad.component.html',
  styleUrls: ['./seguridad.component.css']
})
export class SeguridadComponent {

  cards = [
    {
      titulo: 'Seguridad del Sistema',
      icon: 'fas fa-lock',
      descripcion: 'Implementamos robustas medidas de seguridad informática para garantizar la integridad de cada voto y la protección general de la plataforma.',
      lista: [
        { icon: 'fas fa-shield-alt', texto: 'Conexiones Cifradas (HTTPS): Toda la comunicación entre tu navegador y nuestra plataforma está protegida mediante cifrado SSL/TLS.' },
        { icon: 'fas fa-user-check', texto: 'Acceso Restringido y Autenticado: Solo los ciudadanos registrados pueden acceder al sistema de votación.' },
        { icon: 'fas fa-vote-yea', texto: 'Un Voto por Ciudadano: Mecanismos estrictos para asegurar que cada votante pueda emitir un solo voto.' },
        { icon: 'fas fa-server', texto: 'Infraestructura Protegida: Servidores resguardados con medidas de seguridad física y lógica.' }
      ]
    },
    {
      titulo: 'Protección de Datos Personales y Anonymato',
      icon: 'fas fa-user-secret',
      descripcion: 'Tu privacidad es nuestra prioridad. Manejamos tus datos personales con la máxima confidencialidad y aseguramos el secreto de tu voto.',
      lista: [
        { icon: 'fas fa-id-card', texto: 'Uso Exclusivo para Validación: Tu DNI y nombre se usan solo para verificar tu elegibilidad.' },
        { icon: 'fas fa-mask', texto: 'Separación de Identidad y Voto: Tu voto se procesa de forma anónima.' },
        { icon: 'fas fa-user-slash', texto: 'Confidencialidad Absoluta: Nadie puede conocer individualmente tu voto.' },
        { icon: 'fas fa-gavel', texto: 'Cumplimiento Normativo: Adherimos a las leyes de protección de datos vigentes.' }
      ]
    },
    {
      titulo: 'Transparencia y Auditabilidad',
      icon: 'fas fa-lightbulb',
      descripcion: 'Proceso electoral abierto y verificable para generar confianza en los resultados.',
      lista: [
        { icon: 'fas fa-file-invoice', texto: 'Registro Inmutable de Votos: Cada voto se registra de manera segura e inalterable.' },
        { icon: 'fas fa-eye', texto: 'Resultados Verificables: Datos presentados de forma que permiten auditoría por observadores.' },
        { icon: 'fas fa-cogs', texto: 'Auditorías Independientes: Validación del sistema por entidades externas.' },
        { icon: 'fas fa-fingerprint', texto: 'Rastreo y Trazabilidad: Registros de auditoría sin comprometer la privacidad.' }
      ]
    }
  ];

}
