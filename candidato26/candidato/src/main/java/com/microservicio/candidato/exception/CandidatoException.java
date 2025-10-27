package com.microservicio.candidato.exception;

public class CandidatoException extends Exception {

    private static final long serialVersionUID = 1L;

    public CandidatoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

    public CandidatoException(String mensaje) {
        super(mensaje);
    }
}
