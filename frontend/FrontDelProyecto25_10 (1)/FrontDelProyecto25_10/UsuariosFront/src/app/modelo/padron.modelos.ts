export interface CiudadanoLoginRequest {
  dni: string;
}

export interface CiudadanoLoginResponse {
  mensaje: string;
  tokenDeVoto?: string; 

}


export interface CandidatoDTO {
  id_candidato: number;      
  nombre_candidato: string;   
  apellido_candidato: string; 
  partido: string;
  imagen_candidato: string;   
  rol: string;
}


export interface RegistrarVotoRequest {
  tokenDeVoto: string;
  idPresidente: number | null;     
  idVicepresidente: number | null; 
  idGobernador: number | null;   
}

export interface RegistrarVotoResponse {
  mensaje: string;
}
