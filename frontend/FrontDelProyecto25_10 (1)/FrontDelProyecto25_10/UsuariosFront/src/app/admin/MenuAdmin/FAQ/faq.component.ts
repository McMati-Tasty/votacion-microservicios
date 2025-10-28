import { Component } from '@angular/core';

@Component({
  selector: 'app-faq',
  templateUrl: './faq.component.html',
  styleUrls: ['./faq.component.css']
})
export class FaqComponent {

  faqs = [
    { pregunta: '¿Quiénes son los candidatos?', respuesta: 'Son las personas que se postulan para cargos públicos en una elección.', abierta: false },
    { pregunta: '¿Qué experiencia política o profesional tienen?', respuesta: 'Depende de cada candidato. Algunos tienen trayectoria, otros son nuevos.', abierta: false },
    { pregunta: '¿Qué propuestas concretas tienen?', respuesta: 'Debés revisar su plan de gobierno para conocerlas.', abierta: false },
    { pregunta: '¿Qué postura tienen sobre temas importantes?', respuesta: 'Depende del candidato. Podés comparar sus declaraciones y propuestas.', abierta: false },
    { pregunta: '¿Es confiable?', respuesta: 'Revisá su historial, denuncias y declaraciones anteriores.', abierta: false },
  ];

  infoAdicional = [
    { aspecto: 'Fecha de Elecciones', consideracion: 'Verifica la fecha exacta de las próximas elecciones en tu distrito.' },
    { aspecto: 'Lugar de Votación', consideracion: 'Consulta el padrón electoral para saber dónde te toca votar. ¡Puede cambiar!' },
    { aspecto: 'Documentación Requerida', consideracion: 'Asegúrate de llevar tu DNI (u otra identificación válida) el día de la votación.' },
    { aspecto: 'Horario de Votación', consideracion: 'Conoce los horarios de apertura y cierre de las mesas para organizar tu día.' },
    { aspecto: 'Voto en Blanco / Nulo', consideracion: 'Entiende la diferencia y el impacto de cada uno en el resultado final.' },
    { aspecto: 'Elecciones Primarias (PASO)', consideracion: 'Si aplica en tu país, comprende su objetivo y cómo influyen en las elecciones generales.' },
    { aspecto: 'Cómo Consultar el Padrón', consideracion: 'Generalmente se hace online con tu número de documento. Verifica las fuentes oficiales.' }
  ];

  infoBoxes = [
    { titulo: '¿Cómo sé si estoy habilitado para votar?', descripcion: 'Debes consultar el padrón electoral oficial con tu número de documento. Si apareces, estás habilitado.' },
    { titulo: '¿Qué hago si mi DNI está desactualizado?', descripcion: 'Generalmente, se exige la última versión del DNI. Verifica las normativas de tu país o la autoridad electoral.' },
    { titulo: '¿Puedo votar si estoy de viaje?', descripcion: 'Depende de la legislación electoral de tu país. Algunos permiten el voto en el extranjero con registro previo.' },
    { titulo: '¿Cómo puedo ser autoridad de mesa?', descripcion: 'Las autoridades son designadas por la justicia electoral, pero puedes postularte o ser voluntario en algunos casos.' }
  ];

  toggle(faq: any) {
    faq.abierta = !faq.abierta;
  }

}
